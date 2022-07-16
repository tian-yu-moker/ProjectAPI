package com.hku.projectapi.Programming.Judgement;

import com.hku.projectapi.Beans.Programming.JudgeResult;
import com.hku.projectapi.Beans.Programming.ProgrammingMsg;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Programming.AnswerCode.StringReverseAnswer;
import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class StringReverseJudge
{
    private StringReverseAnswer reverseStringJudge;
    private String filePath;
    private ProgrammingQuestionBean programInfo;
    private String uuid;

    public StringReverseJudge(ProgrammingQuestionBean programInfo, String filePath, String uuid)
    {
        this.reverseStringJudge = new StringReverseAnswer();
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

            }

            return new JudgeResult(ProgrammingMsg.RUNTIME_ERROR, null, "");
        } catch (Exception e){
            return new JudgeResult(ProgrammingMsg.RUNTIME_ERROR, null, e.getMessage());
        }
    }

}
