package com.hku.projectapi.Programming;

import com.baomidou.mybatisplus.annotation.TableField;
import com.hku.projectapi.Beans.Programming.CompileResult;
import com.hku.projectapi.Beans.Programming.ProgrammingHistoryBean;
import com.hku.projectapi.Programming.Dependencies.TreeNode;
import com.hku.projectapi.Programming.Java.JavaCompilers;
import com.hku.projectapi.Programming.Util.AddPreCodes;
import com.hku.projectapi.Programming.Util.FileUtil;
import com.hku.projectapi.Service.Programming.ProgrammingService;
import com.hku.projectapi.Tools.UUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.Date;
import java.util.TimerTask;

/**
 * Create a new thread to execute the current code
 */

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

    private boolean isSuccess;

    private ProgrammingHistoryBean programmingHistoryBean;


    public JavaTaskThread(int questionId, String uploadedCode, String uuid, String userId)
    {
        this.questionId = questionId;
        this.pureCode = uploadedCode;
        this.uploadedCode = uploadedCode;
        this.uuid = uuid + userId;
        this.userId = userId;
        // The path that uploaded coded is saved.
        this.codeSavedPath = System.getProperty("user.home") + "/UserCodes/" + uuid + "/Solution.java";
        // Whether the programming is running
        this.isSuccess = false;
        this.processCode();


    }

    private void processCode()
    {
        // Add dependencies
        this.uploadedCode = AddPreCodes.addCode(this.uploadedCode, "Java");
        // Save the code to a file
        FileUtil.writeFile(uuid, this.uploadedCode);
    }

    // Run the test and uploaded code, no loop is OK.
    @Override
    public void run()
    {
        try{
            // Compile the file
            CompileResult compileResult = JavaCompilers.doCompile(this.codeSavedPath);
            if(compileResult.isSuccess()){
                // Do execution
            }else {
                this.programmingHistoryBean = this.compileFailedHandler(compileResult);
                return;
            }
        }catch (Exception e){

        }
    }

    public ProgrammingHistoryBean compileFailedHandler(CompileResult compileResult)
    {
        ProgrammingHistoryBean history = new ProgrammingHistoryBean();
        history.setQuestionId(this.questionId);
        history.setUserId(this.userId);
        history.setStderr(compileResult.getErrorMsg());
        history.setStatus("Compile error.");
        history.setUploadContent(this.pureCode);
        history.setUuid(this.uuid);
        Date date = new Date();
        Timestamp uploadTime = new Timestamp(date.getTime());
        history.setUploadTime(uploadTime);
        return history;
    }
}
