package com.hku.projectapi.Programming;

import com.hku.projectapi.Beans.Programming.CompileResult;
import com.hku.projectapi.Beans.Programming.ProgrammingHistoryBean;
import com.hku.projectapi.Beans.Programming.ProgrammingMsg;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Programming.Java.JavaCompilers;
import com.hku.projectapi.Programming.Util.AddPreCodes;
import com.hku.projectapi.Programming.Util.FileUtil;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Create a new thread to execute the current code
 */
@Data
public class JavaTaskThread implements Runnable
{
    private int questionId;
    private String pureCode;
    private String uploadedCode;
    // UUID is used to create a unique folder and record in database.
    private String uuid;
    private String userId;
    // The code saved path
    private String codeSavedPath;
    private String purePath;

    private boolean isSuccess;
    private Timestamp uploadTime;

    private ProgrammingHistoryBean programmingHistoryBean;
    private ProgrammingQuestionBean programInfo;


    public JavaTaskThread(ProgrammingQuestionBean programInfo, String uploadedCode, String uuid, String userId)
    {
        this.questionId = programInfo.getId();
        System.out.println(this.questionId + " Create ");
        this.pureCode = uploadedCode;
        this.uploadedCode = uploadedCode;
        this.uuid = uuid;
        this.userId = userId;
        // The path that uploaded coded is saved.
        this.codeSavedPath = System.getProperty("user.home") + "/UserCodes/" + this.uuid + "/" + "a" + this.uuid + ".java";
        this.purePath = "file:///" + System.getProperty("user.home") + "/UserCodes/" + this.uuid + "/";
        // Whether the programming is running
        this.isSuccess = false;
        this.programInfo = programInfo;
        this.programmingHistoryBean = new ProgrammingHistoryBean();
        this.programmingHistoryBean.setStatus("Not finished.");
        Date date = new Date();
        this.uploadTime = new Timestamp(date.getTime());
        this.processCode();
    }

    private void processCode()
    {
        // Add dependencies
        this.uploadedCode = AddPreCodes.addCode(this.uploadedCode, "Java", this.uuid);
        // Save the code to a file
        FileUtil.writeFile(uuid, this.uploadedCode);
        // System.out.println("Write to file...");
    }

    // Run the test and uploaded code, no loop is OK.
    @Override
    public void run()
    {
        // System.out.println("Start...");
        try{
            // Compile the file
            CompileResult compileResult = JavaCompilers.doCompile(this.codeSavedPath);
            if(compileResult.isSuccess()){
                // Do execution
                this.programmingHistoryBean = ExecutionHandler.doExecution(this.programInfo, "Java", this.purePath, this.uuid);
                this.programmingHistoryBean.setUploadedCode(this.pureCode);
                this.programmingHistoryBean.setUploadTime(uploadTime);
                this.programmingHistoryBean.setUserId(this.userId);
            }else {
                this.programmingHistoryBean = this.compileFailedHandler(compileResult);
            }
            return;
        }catch (Exception e){
            this.programmingHistoryBean = this.unknownFailHandler();
            return;
        }
    }

    public ProgrammingHistoryBean compileFailedHandler(CompileResult compileResult)
    {
        ProgrammingHistoryBean history = new ProgrammingHistoryBean();
        history.setQuestionId(this.questionId);
        history.setUserId(this.userId);
        history.setStderr(compileResult.getErrorMsg());
        history.setStatus("Compile error.");
        history.setUploadedCode(this.pureCode);
        history.setUuid(this.uuid);
        history.setUploadTime(this.uploadTime);
        return history;
    }

    public ProgrammingHistoryBean unknownFailHandler()
    {
        ProgrammingHistoryBean history = new ProgrammingHistoryBean();
        history.setQuestionId(this.questionId);
        history.setUserId(this.userId);
        history.setStderr(ProgrammingMsg.RUNTIME_ERROR);
        history.setUploadedCode(this.pureCode);
        history.setUuid(this.uuid);
        history.setUploadTime(this.uploadTime);
        return history;
    }
}
