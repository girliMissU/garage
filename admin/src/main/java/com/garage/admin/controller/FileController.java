package com.garage.admin.controller;


import com.garage.admin.dao.PersonDetectionDAO;
import com.garage.admin.model.PersonDetection;
import com.garage.admin.model.imgTest;
import com.garage.admin.service.imgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Random;


@Controller
public class FileController {
    @Autowired
    private imgService service;

    @Autowired
    private PersonDetectionDAO personDetectionDAO;
    //获取主机端口
   @Value("${com.localhost}")
    private String post;
    //获取本机ip
    private String host;
    //图片存放根路径
    // private String rootPath = "/opt/apache-tomcat-9.0.19/webapps";//上传服务器用
    private String rootPath = "C:";//本机测试用
    //图片存放根目录下的子目录
    private String sonPath = "/upload/";
    //获取图片链接
    private String imgPath;

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @RequestMapping(value = "upload")
    @ResponseBody
    public String upload(@RequestParam("img") MultipartFile file,
                         @RequestParam("ifPerson") String ifPerson) {
        //返回上传的文件是否为空，即没有选择任何文件，或者所选文件没有内容。
        //防止上传空文件导致奔溃
        if (file.isEmpty()) {
            return "file is empty";
        }

        if(ifPerson == null || ifPerson == ""){
            return "if_person is null";
        }
        logger.info("if_person:" + ifPerson);

        Random r = new Random();
        int randNum = r.nextInt(8)+2;//这里就是[2,10)

        //存入是否有人状态
        try {

            PersonDetection personDetection = new PersonDetection();
            personDetection.setId(1);
            personDetection.setGarageId("SZ201712001");
            personDetection.setIfPerson(ifPerson);
            personDetection.setSendTime(new Date());
            personDetectionDAO.updateDetection(personDetection);
            if(ifPerson.equals("1")){
                PersonDetection historyPerson = new PersonDetection();
                historyPerson.setId(randNum);
                historyPerson.setGarageId("SZ201712001");
                historyPerson.setIfPerson(ifPerson);
                historyPerson.setSendTime(new Date());
                personDetectionDAO.updateDetection(historyPerson);
            }

        } catch (Exception e) {
            logger.error("save ifPerson Exception e:", e);
        }

        //获取本机IP
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.error("get server host Exception e:", e);
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();

        //logger.info("上传的文件名为：" + fileName);
        // 设置文件上传后的路径
        String filePath = rootPath + sonPath;
        logger.info("upload file path:" + filePath);
        logger.info("image path:" + host + ":" + post + sonPath + fileName);
        //创建文件路径
        File dest = new File(filePath + fileName);
        String imgPath = (host + ":" + post + sonPath + fileName).toString();

        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;

        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            //假如文件不存在即重新创建新的文件已防止异常发生
            dest.getParentFile().mkdirs();
        }
        try {
            //transferTo（dest）方法将上传文件写到服务器上指定的文件
            file.transferTo(dest);

            //若有人，存为历史图片
            if(ifPerson.equals("1")){
                File historyDest = new File(filePath + randNum + ".jpg");
                logger.info("upload file path:" + filePath + randNum + ".jpg");
                file.transferTo(historyDest);
            }
            //将链接保存到URL中
            //imgTest imgTest = service.add(new imgTest(), imgPath);
            return "[{\"url\":www.garagecloud.cn:" + post + sonPath+ fileName +"}]";
        } catch (Exception e) {
            return "upload failed";
        }
    }
}
