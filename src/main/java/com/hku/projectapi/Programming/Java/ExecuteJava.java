package com.hku.projectapi.Programming.Java;

import com.hku.projectapi.Beans.Programming.AnswerJudgement;
import com.hku.projectapi.Beans.Programming.CompileResult;
import com.hku.projectapi.Beans.Programming.QuestionCode;
import com.hku.projectapi.Programming.Util.FileUtil;
import com.hku.projectapi.Tools.UUidGenerator;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Execute the Java file, which is saved before.
 */
public class ExecuteJava
{
    private String WORK_DIR = System.getProperty("user.dir") + "\\Codes";
    private String CODE_PATH = WORK_DIR + "Solution.java";
    private String CLASS = "Solution";

    private String STDOUT;
    // 程序标准错误放置的文件
    private String STDERR;


    public static void main(String args[])
    {
//        System.out.println(WORK_DIR);
    }

    public ExecuteJava()
    {
        this.WORK_DIR = System.getProperty("user.dir") + "\\Codes\\" + UUidGenerator.getUUID32() + "\\";
        this.CODE_PATH = WORK_DIR + "Solution.java";
        this.CLASS = "Solution";
    }

    public AnswerJudgement executeCode(QuestionCode codes) throws IOException, InterruptedException {
        createDic();
        FileUtil.writeFile(this.CODE_PATH, codes.getCode());
        // Compile the file.
        CompileResult compileResult = JavaCompilers.doCompile(this.CODE_PATH);
        if(compileResult.isSuccess()){
            String runCmd = String.format("java -classpath %s %s", WORK_DIR, CLASS);
            // Run the code
            List<String> res = CommandUtil.run(runCmd, 0);


            return new AnswerJudgement();
        }else{
            return new AnswerJudgement(1, compileResult.getErrorMsg());
        }
        // Create the compile command
//        String compileCmd = String.format("javac -encoding utf-8 %s -d %s",
//                this.CODE_PATH, this.WORK_DIR);
//        CommandUtil.run(compileCmd, null, COMPILE_ERROR);
    }

    // If the dictionary does not exist, create one.
    public void createDic()
    {
        File file = new File(this.WORK_DIR);
        if (!file.exists()) {
            // 创建对应的目录
            file.mkdirs();
        }
    }
}
