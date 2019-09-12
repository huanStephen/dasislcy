package org.eocencle.dasislcy;

import org.apache.commons.lang3.StringUtils;
import org.opencv.core.*;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.*;

/**
 * @Auther: shizh26250
 * @Date: 2019/8/26
 * @Description:
 */
public class AnswerCard {

    public static void main(String[] args) {
//        step1(Constants.PATH + "A4.jpg", 1);
//        step2(Constants.PATH + "card1.png", 100.0);
//        step3(Constants.PATH + "card2.png", 1);
        step4(Constants.PATH + "card3.png");
    }

    public static void step1(String imagefile, Integer ksize) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\n 高斯模糊");

        String sourcePath = imagefile;
        System.out.println("url==============" + sourcePath);
        // 加载为灰度图显示
        Mat source = Highgui.imread(sourcePath, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        Imgproc.GaussianBlur(source, destination, new Size(2 * ksize + 1, 2 * ksize + 1), 0, 0);
        String destPath = Constants.PATH + "card1.png";
        File dstfile = new File(destPath);
        if (StringUtils.isNotBlank(destPath) && dstfile.isFile() && dstfile.exists()) {
            dstfile.delete();
            System.out.println("删除图片：" + destPath);
        }
        Highgui.imwrite(destPath, destination);
        System.out.println("生成目标图片==============" + destPath);

    }

    /**
     * 答题卡识别
     * step2 二值化，反向二值化
     * 创建者 Songer
     * 创建时间	2018年3月22日
     */
    public static void step2(String imagefile, Double thresh) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\n 二值化处理");

        // 灰度化
        // Imgproc.cvtColor(source, destination, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        String sourcePath = imagefile;
        System.out.println("url==============" + sourcePath);
        // 加载为灰度图显示
        Mat source = Highgui.imread(sourcePath, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        Imgproc.threshold(source, destination, thresh, 255, Imgproc.THRESH_BINARY_INV);
        String destPath = Constants.PATH + "card2.png";
        File dstfile = new File(destPath);
        if (StringUtils.isNotBlank(destPath) && dstfile.isFile() && dstfile.exists()) {
            dstfile.delete();
            System.out.println("删除图片：" + destPath);
        }
        Highgui.imwrite(destPath, destination);
        System.out.println("生成目标图片==============" + destPath);

    }

    /**
     * 答题卡识别
     * step3 膨胀腐蚀闭运算(针对反向二值图是开运算)
     * 创建者 Songer
     * 创建时间	2018年3月22日
     */
    public static void step3(String imagefile, Integer ksize) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\n 开运算");

        // 灰度化
        // Imgproc.cvtColor(source, destination, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        String sourcePath = imagefile;
        System.out.println("url==============" + sourcePath);
        // 加载为灰度图显示
        Mat source = Highgui.imread(sourcePath, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        Mat destination = new Mat(source.rows(), source.cols(), source.type());
        Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(2 * ksize + 1, 2 * ksize + 1));

        Imgproc.morphologyEx(source, destination, Imgproc.MORPH_OPEN, element);
        String destPath = Constants.PATH + "card3.png";
        File dstfile = new File(destPath);
        if (StringUtils.isNotBlank(destPath) && dstfile.isFile() && dstfile.exists()) {
            dstfile.delete();
            System.out.println("删除图片：" + destPath);
        }
        Highgui.imwrite(destPath, destination);
        System.out.println("生成目标图片==============" + destPath);
    }

    /**
     * 答题卡识别
     * step4 轮廓识别
     * 创建者 Songer
     * 创建时间	2018年3月22日
     */
    public static void step4(String imagefile) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("\n 轮廓识别");

        // 灰度化
        // Imgproc.cvtColor(source, destination, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        String sourcePath = imagefile;
        System.out.println("url==============" + sourcePath);
        // 加载为灰度图显示
        Mat source = Highgui.imread(sourcePath, Highgui.CV_LOAD_IMAGE_GRAYSCALE);
        Highgui.imwrite("D:\\test\\abc\\source.png", source);
        //此处固定写死，取每一行选项，切割后进行轮廓识别
        Mat ch1 = source.submat(new Rect(170, 52, 294, 32));
        Mat ch2 = source.submat(new Rect(170, 104, 294, 32));
        Mat ch3 = source.submat(new Rect(170, 156, 294, 32));
        Mat ch4 = source.submat(new Rect(170, 208, 294, 32));
        Mat ch5 = source.submat(new Rect(170, 260, 294, 32));

        Mat ch6 = source.submat(new Rect(706, 50, 294, 32));
        Mat ch7 = source.submat(new Rect(706, 104, 294, 32));
        Mat ch8 = source.submat(new Rect(706, 156, 294, 32));
        Mat ch9 = source.submat(new Rect(706, 208, 294, 32));
        Mat ch10 = source.submat(new Rect(706, 260, 294, 32));

        Mat ch11 = source.submat(new Rect(1237, 50, 294, 32));
        Mat ch12 = source.submat(new Rect(1237, 104, 294, 32));
        Mat ch13 = source.submat(new Rect(1237, 156, 294, 32));
        Mat ch14 = source.submat(new Rect(1237, 208, 294, 32));
        Mat ch15 = source.submat(new Rect(1237, 260, 294, 32));

        Mat ch16 = source.submat(new Rect(1766, 50, 294, 32));
        Mat ch17 = source.submat(new Rect(1766, 104, 294, 32));
        Mat ch18 = source.submat(new Rect(1766, 156, 294, 32));
        Mat ch19 = source.submat(new Rect(1766, 208, 294, 32));
        Mat ch20 = source.submat(new Rect(1766, 260, 294, 32));

        Mat ch21 = source.submat(new Rect(170, 358, 294, 32));
        Mat ch22 = source.submat(new Rect(170, 410, 294, 32));
        Mat ch23 = source.submat(new Rect(170, 462, 294, 32));
        Mat ch24 = source.submat(new Rect(170, 514, 294, 32));
        Mat ch25 = source.submat(new Rect(170, 566, 294, 32));
        List<Mat> chlist = new ArrayList<Mat>();
        chlist.add(ch1);
        chlist.add(ch2);
        chlist.add(ch3);
        chlist.add(ch4);
        chlist.add(ch5);
        chlist.add(ch6);
        chlist.add(ch7);
        chlist.add(ch8);
        chlist.add(ch9);
        chlist.add(ch10);
        chlist.add(ch11);
        chlist.add(ch12);
        chlist.add(ch13);
        chlist.add(ch14);
        chlist.add(ch15);
        chlist.add(ch16);
        chlist.add(ch17);
        chlist.add(ch18);
        chlist.add(ch19);
        chlist.add(ch20);
        chlist.add(ch21);
        chlist.add(ch22);
        chlist.add(ch23);
        chlist.add(ch24);
        chlist.add(ch25);

        Mat hierarchy = new Mat();
        java.util.TreeMap<Integer,String> listenAnswer = new TreeMap<Integer,String>();
        for (int no=0;no<chlist.size();no++) {
            Vector<MatOfPoint> contours = new Vector<MatOfPoint>();
            Mat ch = chlist.get(no);
            Imgproc.findContours(ch, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE, new Point());

            Vector<RectComp> rectCompList = new Vector<RectComp>();
            for(int i = 0;i<contours.size();i++){
                MatOfPoint mop= contours.get(i);
                // 获取轮廓外矩，即使用最小矩形将轮廓包裹
                Rect rm = Imgproc.boundingRect(mop);
                RectComp rc = new RectComp(rm);
                rectCompList.add(rc);
            }
            // System.out.println(no+"size="+rectCompList.size());
            Collections.sort(rectCompList);
            // for(int t = 0;t<rectCompList.size();t++){
            // RectComp rect = rectCompList.get(t);
            // System.out.println(rect.getRm().area() + "--------" + rect.getRm().x);
            // if (rect.getRm().area() < 300) {// 小于300的pass，完美填图的话是≈1500
            // continue;
            // }
            // if (rect.getRm().x < 68) {
            // listenAnswer.put(Integer.valueOf(no), "A");
            // } else if ((rect.getRm().x > 68) && (rect.getRm().x < 148)) {
            // listenAnswer.put(Integer.valueOf(no), "B");
            // } else if ((rect.getRm().x > 148) && (rect.getRm().x < 228)) {
            // listenAnswer.put(Integer.valueOf(no), "C");
            // } else if (rect.getRm().x > 228) {
            // listenAnswer.put(Integer.valueOf(no), "D");
            // }
            // }
            // 因为已经按面积排序了，所以取第一个面积最大的轮廓即可
            RectComp rect = rectCompList.get(0);
            System.out.println(rect.getRm().area() + "--------" + rect.getRm().x);
            if (rect.getRm().area() > 300) {// 小于300的pass，说明未填写，完美填图的话是≈1500
                if (rect.getRm().x < 68) {
                    listenAnswer.put(Integer.valueOf(no), "A");
                } else if ((rect.getRm().x > 68) && (rect.getRm().x < 148)) {
                    listenAnswer.put(Integer.valueOf(no), "B");
                } else if ((rect.getRm().x > 148) && (rect.getRm().x < 228)) {
                    listenAnswer.put(Integer.valueOf(no), "C");
                } else if (rect.getRm().x > 228) {
                    listenAnswer.put(Integer.valueOf(no), "D");
                }
            } else {
                listenAnswer.put(Integer.valueOf(no), "未填写");
            }

            Mat result = new Mat(ch.size(), CvType.CV_8U, new Scalar(255));
            Imgproc.drawContours(result, contours, -1, new Scalar(0, 255, 0), 2);
            String destPath = Constants.PATH + "ch" + (no + 1) + ".png";
            File dstfile = new File(destPath);
            if (StringUtils.isNotBlank(destPath) && dstfile.isFile() && dstfile.exists()) {
                dstfile.delete();
                System.out.println("删除图片：" + destPath);
            }
            Highgui.imwrite(destPath, result);
            System.out.println("生成目标图片==============" + result);
        }
        String resultValue = "最终结果：试题编号-答案<br> ";
        for (Integer key : listenAnswer.keySet()) {
            resultValue += "【" + (key + 1) + ":" + listenAnswer.get(key) + "】";
            if ((key + 1) % 5 == 0) {
                resultValue += "<br>";
            }
        }
    }

}
