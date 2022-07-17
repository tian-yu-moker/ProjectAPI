package com.hku.projectapi.Programming.Judgement;

import com.hku.projectapi.Beans.Programming.JudgeResult;
import com.hku.projectapi.Beans.Programming.ProgrammingMsg;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Programming.AnswerCode.StringReverseAnswer_2;
import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class StringReverseJudge_2
{
    private StringReverseAnswer_2 reverseStringJudge;
    private String filePath;
    private ProgrammingQuestionBean programInfo;
    private String uuid;

    public StringReverseJudge_2(ProgrammingQuestionBean programInfo, String filePath, String uuid)
    {
        this.reverseStringJudge = new StringReverseAnswer_2();
        this.programInfo = programInfo;
        this.filePath = filePath;
        this.uuid = uuid;
    }

    public JudgeResult doJudge()
    {
        try {
            URL url = new URL(this.filePath);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});
            Class<?> cls = Class.forName(this.uuid, true, classLoader);
            Object instance = cls.newInstance();
            Method method = cls.getDeclaredMethod("reverseString", String.class);
            method.setAccessible(true);
            List<GeneralBean> testCases = this.programInfo.getTestCases();

            for(GeneralBean cases:testCases){
                String param1 = (String) cases.getParam1();
                String resTruth = reverseStringJudge.reverseString(param1);
                String resUser = (String) method.invoke(instance, param1);
                if(!resTruth.equals(resUser)){
                    GeneralBean failedCase = new GeneralBean();
                    failedCase.setParam1(cases.getParam1());
                    return new JudgeResult("Reject", cases, ProgrammingMsg.CASE_NOT_PASS);
                }
            }
            JudgeResult result = new JudgeResult();
            result.setStatus("Accept");
            return result;
        } catch (Exception e){
            return new JudgeResult(ProgrammingMsg.RUNTIME_ERROR, null, e.getMessage());
        }
    }

}
