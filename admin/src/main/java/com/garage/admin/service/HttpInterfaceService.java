package com.garage.admin.service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * @author LIFAN
 * 2019/3/5 19:08
 */
@Service
public class HttpInterfaceService {
    /**
     * 调用对方接口方法
     * @param path 对方或第三方提供的路径
     * @param data 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
     */
    public static Object HttpInterfaceUtil(String path,String data) {
        String result = "";
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            PrintWriter out = null;
            //请求方式
            conn.setRequestMethod("POST");
            //设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，
            // 此外发送post请求必须设置这两个
            //最常用的Http请求无非是get和post，get请求可以获取静态页面，也可以把参数放在URL字串后面，传递给servlet，
            //post与get的 不同之处在于post的参数不是放在URL字串里面，而是放在http请求的正文内。
            conn.setDoOutput(true);
            conn.setDoInput(true);
            //获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            //发送请求参数即数据
            out.print(data);
            //缓冲数据
            out.flush();
            //获取URLConnection对象对应的输入流
            InputStream is = conn.getInputStream();
            //构造一个字符流缓存
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";

            while ((str = br.readLine()) != null) {
                result = result + str;
            }

            //关闭流
            is.close();
            //断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            //固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            conn.disconnect();
            System.out.println("完整结束");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void saveImage(String path, String imgName) {
        try {
            URL url = new URL(path);
            //打开和url之间的连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //请求方式
            conn.setRequestMethod("GET");
            // 超时响应时间为5秒
            conn.setConnectTimeout(5 * 1000);
            // 通过输入流获取图片数据
            InputStream inStream = conn.getInputStream();
            // 得到图片的二进制数据，以二进制封装得到数据，具有通用性
            byte[] data1 = readInputStream(inStream);
            // new一个文件对象用来保存图片，默认保存当前工程根目录

            File imageFile = new File("C:/LIFAN/garage_img/" + imgName);
            // 创建输出流
            FileOutputStream outStream = new FileOutputStream(imageFile);
            // 写入数据
            outStream.write(data1);
            // 关闭输出流
            outStream.close();
            System.out.println("完成！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            // 创建一个Buffer字符串
            byte[] buffer = new byte[1024];
            // 每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            // 使用一个输入流从buffer里把数据读取出来
            while ((len = inStream.read(buffer)) != -1) {
                // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
                outStream.write(buffer, 0, len);
            }
            // 关闭输入流
            inStream.close();
            // 把outStream里的数据写入内存
            return outStream.toByteArray();

        }

    //获取当前监控的实时画面（图片url）
    public String getAndSaveImage(String imgName){
        //首先获取accessToken
        String data_0 = "appKey=d6cc784603ee433c82f62cae2bcd1e27&appSecret=65a8cfd87608ec35775ae56078917e44";
        String result_0 = HttpInterfaceUtil("https://open.ys7.com/api/lapp/token/get", data_0).toString();
        JSONObject jsStr_0 = JSONObject.parseObject(result_0);
        System.out.println(jsStr_0);
        String resultData_0 = jsStr_0.getString("data");
        JSONObject jsStr_01 = JSONObject.parseObject(resultData_0);
        //获取url的值
        String accessToken = jsStr_01.getString("accessToken");

        String data = "accessToken=" + accessToken + "&deviceSerial=C59706664&channelNo=1";
        String result = HttpInterfaceUtil("https://open.ys7.com/api/lapp/device/capture", data).toString();
        JSONObject jsStr = JSONObject.parseObject(result);
        System.out.println(jsStr);
        String resultData = jsStr.getString("data");
        JSONObject jsStr1 = JSONObject.parseObject(resultData);
        //获取url的值
        String picURL = jsStr1.getString("picUrl");
        //System.out.println(picURL);
        //saveImage(picURL,imgName);
        return picURL;
    }


    public static void main(String[] args) {
        String data = "accessToken=at.6pbzfm8m9a1g805j83b784l739jq180c-8abb94h4zv-1g4cv74-kvv44eryt&deviceSerial=C59706664&channelNo=1";
        String result = HttpInterfaceUtil("https://open.ys7.com/api/lapp/device/capture", data).toString();
        JSONObject jsStr = JSONObject.parseObject(result);
        System.out.println(jsStr);
        String resultData = jsStr.getString("data");
        JSONObject jsStr1 = JSONObject.parseObject(resultData);
        //获取url的值
        String picURL = jsStr1.getString("picUrl");
        System.out.println(picURL);
        saveImage(picURL,"2019-12-18.png");

        //System.out.println(HttpInterfaceService(picURL, "").toString());
//        interfaceUtil("http://192.168.10.89:8080/eoffice-restful/resources/sys/oadata", "usercode=10012");
//        interfaceUtil("http://192.168.10.89:8080/eoffice-restful/resources/sys/oaholiday",
//                    "floor=first&year=2017&month=9&isLeader=N");
    }
}

