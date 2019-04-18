package org.eocencle.dasislcy.util;

import jdk.nashorn.internal.runtime.regexp.RegExp;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: shizh26250
 * @Date: 2019/3/29 11:29
 * @Description:
 */
public class FileUtil {

    /**
     * 读取文件
     * @param filePath
     * @return
     * @throws IOException
     */
    public static String readFile(String filePath) throws IOException {
        //读取文件(缓存字节流)
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(filePath));

        //读取数据
        //一次性取多少字节
        byte[] bytes = new byte[2048];
        //接受读取的内容(n就代表的相关数据，只不过是数字的形式)
        int n = -1;
        //循环取出数据
        StringBuffer sb = new StringBuffer();
        while ((n = in.read(bytes,0,bytes.length)) != -1) {
            //转换成字符串
            sb.append(new String(bytes,0,n,"UTF-8"));
        }

        //关闭流
        in.close();

        return sb.toString();
    }

    /**
     * 写入文件
     * @param filePath
     * @param content
     * @throws IOException
     */
    public static void writeFile(String filePath, String content) throws IOException {
        //写入相应的文件
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(filePath));

        //写入相关文件
        out.write(content.getBytes("UTF-8"));

        out.close();
    }

    public static void plan1(Matcher m) {
        String reg_option1 = "A.([^B.]+)B.([^C.]+)C.([^D.]+)D.(.*)";
        String title = "", option = "";
        title = m.group(1);
        option = m.group(2);

        Pattern r = Pattern.compile(reg_option1);
        Matcher m1 = r.matcher(option.trim());
        System.out.println(title);
        System.out.println(option);
        if (m1.find()) {
            System.out.println(m1.group(1).replaceAll(" ", "").trim());
            System.out.println(m1.group(2).replaceAll(" ", "").trim());
            System.out.println(m1.group(3).replaceAll(" ", "").trim());
            System.out.println(m1.group(4).replaceAll(" ", "").trim());
        }
    }

    public static void plan2(Matcher m) {
        String reg_option1 = "A.([^B.]+)B.(.*)";
        String reg_option2 = "C.([^D.]+)D.(.*)";
        String title = "", option1 = "", option2 = "";
        title = m.group(1);
        option1 = m.group(2);
        option2 = m.group(3);

        Pattern r1 = Pattern.compile(reg_option1);
        Pattern r2 = Pattern.compile(reg_option2);
        Matcher m1 = r1.matcher(option1.trim());
        Matcher m2 = r2.matcher(option2.trim());
        System.out.println(title);
        System.out.println(option1);
        System.out.println(option2);
        if (m1.find()) {
            System.out.println(m1.group(1).replaceAll(" ", "").trim());
            System.out.println(m1.group(2).replaceAll(" ", "").trim());
        }

        if (m2.find()) {
            System.out.println(m2.group(1).replaceAll(" ", "").trim());
            System.out.println(m2.group(2).replaceAll(" ", "").trim());
        }
    }

    public static void plan3(Matcher m) {
        String reg_option1 = "A.([^B.]+)B.(.*)";
        String reg_option2 = "C.([^D.]+)D.(.*)";
        String title = "", option1 = "", option2 = "";
        title = m.group(1) + "\n" + m.group(2);
        option1 = m.group(3);
        option2 = m.group(4);

        Pattern r1 = Pattern.compile(reg_option1);
        Pattern r2 = Pattern.compile(reg_option2);
        Matcher m1 = r1.matcher(option1.trim());
        Matcher m2 = r2.matcher(option2.trim());
        System.out.println(title);
        System.out.println(option1);
        System.out.println(option2);
        if (m1.find()) {
            System.out.println(m1.group(1).replaceAll(" ", "").trim());
            System.out.println(m1.group(2).replaceAll(" ", "").trim());
        }

        if (m2.find()) {
            System.out.println(m2.group(1).replaceAll(" ", "").trim());
            System.out.println(m2.group(2).replaceAll(" ", "").trim());
        }
    }

    public static void main(String[] args) throws IOException {
        String reg_whole1 = "<div[^>]*><a[^>]*><p[^>]*>([^<>]*)</p[^>]*><p[^>]*>([^<>]*)</p[^>]*></a></div>";
        Pattern r1 = Pattern.compile(reg_whole1);

        String reg_whole2 = "<div[^>]*><a[^>]*><p[^>]*>([^<>]*)</p[^>]*><p[^>]*>([^<>]*)</p[^>]*><p[^>]*>([^<>]*)</p[^>]*></a></div>";
        Pattern r2 = Pattern.compile(reg_whole2);

        String reg_whole3 = "<div[^>]*><a[^>]*><p[^>]*>([^<>]*)</p[^>]*><p[^>]*>([^<>]*)</p[^>]*><p[^>]*>([^<>]*)</p[^>]*><p[^>]*>([^<>]*)</p[^>]*></a></div>";
        Pattern r3 = Pattern.compile(reg_whole3);

        String path = "C:\\Users\\shizh\\Desktop\\h1\\词法";

        for (int i = 1; i <= 10; i ++) {
            System.out.println(i);
            String content = readFile(path + i).replaceAll("\r", "").replaceAll("\n", "");
            Matcher m1 = r1.matcher(content);

            if (m1.matches()) {
                plan1(m1);
                move(path + i, "C:\\Users\\shizh\\Desktop\\ok");
            } else {
                Matcher m2 = r2.matcher(content);

                if (m2.matches()) {
                    plan2(m2);
                    move(path + i, "C:\\Users\\shizh\\Desktop\\ok");
                } else {
                    Matcher m3 = r3.matcher(content);

                    if (m3.matches()) {
                        plan3(m3);
                        move(path + i, "C:\\Users\\shizh\\Desktop\\ok");
                    }
                }
            }
        }
    }

    /**
     * 移动文件
     * @param srcFile
     * @param destPath
     * @return
     */
    public static boolean move(String srcFile, String destPath){
        // File (or directory) to be moved
        File file = new File(srcFile);

        // Destination directory
        File dir = new File(destPath);

        // Move file to new directory
        boolean success = file.renameTo(new File(dir, file.getName()));

        return success;
    }

}
