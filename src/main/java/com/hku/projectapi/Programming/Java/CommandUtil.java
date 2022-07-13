package com.hku.projectapi.Programming.Java;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// 为了能执行javac编译命令，和java运行指令。
public class CommandUtil {
    // run 方法，要执行一个命令就要创建一个进程来运行一个命令。
    // 要进行编译和运行，来确定这个类中的参数，包含3个参数。
    // 1.要进行编译的命令
    // 2.要是编译和运行都成功，则是标准输出的内容，放在一个文件中，用stdoutFile来表示。
    // 3.要是编译出错或者运行出错，则表示的是标准错误，放在stderrFile文件中。
    // Run方法用于进行创建进程并执行编译和运行命令。
    // cmd 表示要执行的命令, 比如 javac
    // stdoutFile 指定标准输出写到哪个文件中
    // stderrFile 执行标准错误写到哪个文件中
    // 进程的返回值用0来表示进程结束，-1表示的是进程没有结束，或者进程异常。
    public static List run(String cmd, int type) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(cmd);
        List<String> stdErrOut = new ArrayList<>();
        if(type == 1)
        {
            InputStream stdoutFrom = process.getInputStream();
            String result = new BufferedReader(new InputStreamReader(stdoutFrom))
                    .lines().collect(Collectors.joining(System.lineSeparator()));
            stdErrOut.add(result);

            InputStream stderrFrom = process.getErrorStream();
            String error = new BufferedReader(new InputStreamReader(stderrFrom))
                    .lines().collect(Collectors.joining(System.lineSeparator()));
            stdErrOut.add(error);
        }

        // 同上，再针对标准错误进行重定向
        // 要是标准错误文件中有内容，就将输出的内容进行重定向。
        // 等待新的进程的结束，在结束退出码。就是等待刚创建的run进程的结束才获取退出码的
        int exitCode = process.waitFor();
        stdErrOut.add(String.valueOf(exitCode));
        return stdErrOut;
    }

//    public static void main(String[] args) {
//        // 下边来进行调用。
//        try {
//            // run方法的返回值是int型的，0表示进程成功结束，-1表示的是错误。
//            int ret = CommandUtil.run("javac", "./stdout.txt", "./stderr.txt");
//            System.out.println(ret);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
