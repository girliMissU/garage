package com.garage.admin.service;

import org.opencv.core.*;
import org.opencv.dnn.*;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;

import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.FileOutputStream;
import java.io.OutputStream;


/**
 * @author LIFAN
 * 2018/12/4 12:36
 */

@Service
public class FaceDetectService {
    static {
        // 载入opencv的库
//        String opencvpath = System.getProperty("user.dir") + "\\opencv\\x64\\";
//        String opencvDllName = opencvpath + Core.NATIVE_LIBRARY_NAME + ".dll";
//        System.load(opencvDllName);
    }


    //base64字符串转化成图片
    public static boolean GenerateImage(String imgStr,String name) {
        //将前端传过来的Base64解码并生成图片
        //判断图像数据为空
        if (imgStr == null) return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //生成图片
            String imgFilePath = "C:\\LIFAN\\garage_img\\" + name;
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            return false;
        }
    }


    /**
     * opencv实现DNN.Darknet接口进行目标检测
     *
     * @param path
     * @param outFile
     * @throws Exception
     */
    public static boolean DarknetDetect(String path, String outFile) throws Exception {

        boolean flag = false;
        String[] names = new String[]{
                "aeroplane","bicycle","bird","boat","bottle",
                "bus","car","cat","chair","cow",
                "diningtable","dog","horse","motorbike","person",
                "pottedplant","sheep","sofa","train","tvmonitor"
        };

        //System.loadLibrary( "opencv_java400.dll" );
        System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
        //Net net = Dnn.readNetFromDarknet("tiny-yolo-voc.cfg", "tiny-yolo-voc.weights");
        Net net = Dnn.readNetFromDarknet(System.getProperty("user.dir") + "\\opencv\\yolov2-voc.cfg", System.getProperty("user.dir") + "\\opencv\\yolov2-voc.weights");
        //Net net = Dnn.readNetFromDarknet(System.getProperty("user.dir") + "\\opencv\\yolov3.cfg", System.getProperty("user.dir") + "\\opencv\\yolov3.weights");

        if ( net.empty() ) {
            System.out.println("Reading Net error");
        }

        String image_file = path;//IMG_9452.JPG
        Mat im = Imgcodecs.imread(image_file, Imgcodecs.IMREAD_COLOR);
        if( im.empty() ) {
            System.out.println("Reading Image error");
        }

        Mat frame = new Mat();
        Size sz1 = new Size(im.cols(),im.rows());
        Imgproc.resize(im, frame, sz1);

        Mat resized = new Mat();
        Size sz = new Size(416,416);
        Imgproc.resize(im, resized, sz);

        float scale = 1.0F / 255.0F;
        Mat inputBlob = Dnn.blobFromImage(im, scale, sz, new Scalar(0), false, false);
        net.setInput(inputBlob, "data");
        Mat detectionMat = net.forward("detection_out");
        if( detectionMat.empty() ) {
            System.out.println("No result");
        }

        for (int i = 0; i < detectionMat.rows(); i++)
        {
            int probability_index = 5;
            int size = (int) (detectionMat.cols() * detectionMat.channels());

            float[] data = new float[size];
            detectionMat.get(i, 0, data);
            float confidence = -1;
            int objectClass = -1;
            for (int j=0; j < detectionMat.cols();j++)
            {
                if (j>=probability_index && confidence<data[j])
                {
                    confidence = data[j];
                    objectClass = j-probability_index;
                }
            }

            if (confidence > 0.3)
            {
                System.out.println("Result Object: "+i);
                for (int j=0; j < detectionMat.cols();j++)
                    System.out.print(" "+j+":"+ data[j]);
                System.out.println("");
                float x = data[0];
                float y = data[1];
                float width = data[2];
                float height = data[3];
                float xLeftBottom = (x - width / 2) * frame.cols();
                float yLeftBottom = (y - height / 2) * frame.rows();
                float xRightTop = (x + width / 2) * frame.cols();
                float yRightTop = (y + height / 2) * frame.rows();

                System.out.println("Class: "+ names[objectClass]);

                if(names[objectClass] == "person"){
                    flag = true;
                }
                System.out.println("Confidence: "+confidence);

                System.out.println("ROI: "+xLeftBottom+" "+yLeftBottom+" "+xRightTop+" "+yRightTop+"\n");

                Imgproc.rectangle(frame, new Point(xLeftBottom, yLeftBottom),
                        new Point(xRightTop,yRightTop),new Scalar(0, 255, 0),3);
            }
        }
        Imgcodecs.imwrite(outFile, frame );
        return flag;
    }

    public static String test() throws Exception {
        DarknetDetect("C:\\LIFAN\\garage_img\\man\\000010.png", "C:\\LIFAN\\garage_img\\test\\Yolo_Detect_test.png");
        return "success";
    }

    public static void main(String[] args) throws Exception {
        //人脸识别
        //detectFace("C:\\LIFAN\\garage_img\\2018-12-07 14_13_16.png", "C:\\LIFAN\\garage_img\\personFaceDetect.png");
        //HogDetect("C:\\LIFAN\\garage_img\\2018-12-07 14_13_16.png", "C:\\LIFAN\\garage_img\\HogDetect.png");

        int count = 0;
        int correct = 0;
        for(int i=10;i<20;i++){
            count++;
            if(DarknetDetect("C:\\LIFAN\\garage_img\\man\\0000" + i + ".png", "C:\\LIFAN\\garage_img\\Yolo_Detect"+ i +".png")){
                correct++;
            }
        }

        System.out.println("测试样本数: " + count + " , 识别成功数为: " + correct + " , 准确率为: " + (double)correct/(double)count);

    }
}