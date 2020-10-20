package com.garage.admin.service.wenda;

import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class SensitiveService implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveService.class);

    /**
     * 默认敏感词替换符
     */
    private static final String DEFAULT_REPLACEMENT = "敏感词";

    //前缀树节点结构
    private class TrieNode {

        /**
         * true 关键词的终结 ； false 继续
         */
        private boolean end = false;

        /**
         * key下一个字符，value是对应的节点
         */
        private Map<Character, TrieNode> subNodes = new HashMap<>();

        /**
         * 向指定位置添加节点树
         */
        void addSubNode(Character key, TrieNode node) {
            subNodes.put(key, node);
        }

        /**
         * 获取下个节点
         */
        TrieNode getSubNode(Character key) {
            return subNodes.get(key);
        }

        boolean isKeywordEnd() {
            return end;
        }

        void setKeywordEnd(boolean end) {
            this.end = end;
        }

        public int getSubNodeCount() {
            return subNodes.size();
        }

    }

    /**
     * 根节点
     */
    private TrieNode rootNode = new TrieNode();

    /**
     * 判断是否是一个符号
     */
    private boolean isSymbol(char c) {
        int ic = (int) c;
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        //判断是否为中文标点
        if (ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS
                || ub == Character.UnicodeBlock.VERTICAL_FORMS){
            return true;
        }
        // 0x2E80-0x9FFF 东亚文字范围
        return Character.isSpaceChar(c)||(!CharUtils.isAsciiAlphanumeric(c) && (ic < 0x2E80 || ic > 0x9FFF));
    }

    /**
     * 过滤敏感词
     */
    public String filter(String text) {
        if (StringUtils.isBlank(text)) {
            return text;
        }
        String replacement = DEFAULT_REPLACEMENT;
        StringBuilder result = new StringBuilder();

        TrieNode tempNode = rootNode;

        // 回滚数
        int begin = 0;

        // 当前比较的位置
        int position = 0;

        while (position < text.length()) {
            char c = text.charAt(position);
            // 这里的比较是为了忽略敏感词间的特殊字符，比如 色-_-情
            if (isSymbol(c)) {
                // 如果是开头，那就直接写入结果字符串
                if (tempNode == rootNode) {
                    result.append(c);
                    ++begin;
                }
                ++position;
                continue;
            }
            // 读取该字符对应的子节点
            tempNode = tempNode.getSubNode(c);

            // 不存在，换下个字符开头
            if (tempNode == null) {
                // 以begin开始的字符串不存在敏感词
                result.append(text.charAt(begin));
                // 跳到下一个字符开始测试
                position = begin + 1;
                begin = position;
                // 回到树初始节点
                tempNode = rootNode;
            } else if (tempNode.isKeywordEnd()) {
                // 发现敏感词， 从begin到position的位置用replacement替换掉
                result.append(replacement);
                position = position + 1;
                begin = position;
                tempNode = rootNode;
            } else {
                // 有这个词，但不是敏感词，继续看看后面的词
                ++position;
            }
        }

        result.append(text.substring(begin));

        return result.toString();
    }


    //构建前缀树结构
    private void addWord(String lineTxt) {
        TrieNode tempNode = rootNode;
        // 循环每个字节
        for (int i = 0; i < lineTxt.length(); ++i) {
            Character c = lineTxt.charAt(i);
            // 过滤空格和符号
            if (isSymbol(c)) {
                continue;
            }
            TrieNode node = tempNode.getSubNode(c);
            // 没初始化
            if (node == null) {
                node = new TrieNode();
                tempNode.addSubNode(c, node);
            }

            tempNode = node;

            if (i == lineTxt.length() - 1) {
                // 关键词结束， 设置结束标志
                tempNode.setKeywordEnd(true);
            }
        }
    }


    //我们通过这个函数，在每次注入该服务时，能够初始化前缀树
    @Override
    public void afterPropertiesSet() throws Exception {
        rootNode = new TrieNode();

        try {
            InputStream is = Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("SensitiveWords.txt");
            InputStreamReader read = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                lineTxt = lineTxt.trim();
                addWord(lineTxt);
            }
            read.close();
        } catch (Exception e) {
            logger.error("读取敏感词文件失败" + e.getMessage());
        }
    }

    public static void main(String[] argv) {
        SensitiveService s = new SensitiveService();
        s.addWord("色情");
        s.addWord("好色");
        System.out.print(s.filter("你好X色。**情XX色--情x"));
    }
}
