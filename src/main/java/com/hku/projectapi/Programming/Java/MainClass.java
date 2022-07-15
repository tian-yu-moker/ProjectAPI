package com.hku.projectapi.ProgrammingUtil.Java;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainClass
{
    public static void main(String[] argc){
        if(argc.length != 1){
            System.out.println("Invalid parameter!(java filename {listen-port})");
        }
        int lestenPort = 8080;

        //初始化线程池，对于线程池的使用可百度
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        try {
            //监听一个端口，网站后端会发封装好的任务过来。
            ServerSocket serverSocket = new ServerSocket(lestenPort);
            while(true){
                //测试阶段，
                //测试用，模仿用户提交它的代码
                String content = "01234567890123456789012345678901\n" +
                        "import java.util.Scanner;\n" +
                        "import java.io.IOException;\n" +
                        "\n" +
                        "public class a01234567890123456789012345678901{\n" +
                        "    public static void main(String[] args) throws IOException{\n" +
                        "        Scanner input = new Scanner(System.in);\n" +
                        "        int len = Integer.parseInt(input.nextLine());\n" +
                        "        for(int i = 0; i < len; i++){\n" +
                        "            System.out.println(input.nextLine());\n" +
                        "        }\n" +
                        "        input.close();\n" +
                        "    }\n" +
                        "}";
                break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
