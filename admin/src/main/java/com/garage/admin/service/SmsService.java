package com.garage.admin.service;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.stereotype.Service;

/**
 * @author LIFAN
 * 2019/3/15 17:50
 * 短信服务
 */
@Service
public class SmsService {
    public static void sendSms(String phoneNum,String contact,String trigger) {
//        DefaultProfile profile = DefaultProfile.getProfile("default", "", "");//key与密钥在阿里云上查找
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        CommonRequest request = new CommonRequest();
//        // request.setProtocol(ProtocolType.HTTPS);
//        request.setMethod(MethodType.POST);
//        // 产品域名,开发者无需替换
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("SendSms");
//        // 必填:待发送手机号
//        request.putQueryParameter("PhoneNumbers", phoneNum);
//        // 必填:短信签名-可在短信控制台中找到
//        request.putQueryParameter("SignName", "聚力立停");
//        // 必填:短信模板-可在短信控制台中找到
//        request.putQueryParameter("TemplateCode", "SMS_160306068");
//        // 模板中的变量替换JSON串
//        String json = "{\"name\":\""+ contact +"\",\"trigger\":\""+ trigger +"\"}";
//        request.putQueryParameter("TemplateParam", json);
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
    }
    public static void main(String[] args){
        sendSms("18795968928","lifan","yikesaiting");
    }
}
