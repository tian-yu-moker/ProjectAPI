package com.hku.projectapi.Programming.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// 使用这个类封装一下 Java 的文件操作.
// 让后面的代码能够更方便的读写整个文件.
public class FileUtil {
    // 从指定的文件中一次把所有的内容读出来
    public static String readFile(String filePath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileInputStream fileInputStream = new FileInputStream(filePath);) {
            while (true) {
                int ch = fileInputStream.read();
                if (ch == -1) {
                    break;
                }
                // 每次 read 方法只是读到 一个字节 .
                // read 设计成返回 int 的原因只是为了能多表示一下 -1 这个情况
                // 实际把读取的结果往 stringBuilder 里插入的时候, 还得再转换回 byte 类型
                // 预期是一次往 StringBuilder 写一个字符给 StringBuilder, 而不是一次写 四个字节
                stringBuilder.append((char)ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    // Save contents into the target path, if no, create one
    public static void writeFile(String uuid, String content)
    {
        String filePath = System.getProperty("user.home") + "/UserCodes/" + uuid + "/Solution.java";
        File file = new File(filePath);
        if (!file.exists()) {
            // 创建对应的目录
            file.mkdirs();
        }
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            // 进行写文件操作
            fileOutputStream.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
