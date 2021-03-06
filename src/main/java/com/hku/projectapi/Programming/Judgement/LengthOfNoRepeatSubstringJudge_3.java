package com.hku.projectapi.Programming.Judgement;

import com.hku.projectapi.Beans.Programming.JudgeResult;
import com.hku.projectapi.Beans.Programming.ProgrammingMsg;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Programming.AnswerCode.LengthOfNoRepeatSubstringAnswer_3;
import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class LengthOfNoRepeatSubstringJudge_3
{
    private LengthOfNoRepeatSubstringAnswer_3 lengthOfNoRepeatSubstringAnswer3;
    private String filePath;
    private ProgrammingQuestionBean programInfo;
    private String uuid;

    public LengthOfNoRepeatSubstringJudge_3(ProgrammingQuestionBean programInfo, String filePath, String uuid)
    {
        this.lengthOfNoRepeatSubstringAnswer3 = new LengthOfNoRepeatSubstringAnswer_3();
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
            Method method = cls.getDeclaredMethod("lengthOfLongestSubstring", String.class);
            method.setAccessible(true);
            List<GeneralBean> testCases = this.programInfo.getTestCases();

            for(GeneralBean cases:testCases){
                String param1 = (String) cases.getParam1();
                int resTruth = this.lengthOfNoRepeatSubstringAnswer3.lengthOfLongestSubstring(param1);
                int resUser = (int) method.invoke(instance, param1);
                if(resTruth != resUser){
                    GeneralBean failedCase = new GeneralBean();
                    failedCase.setParam1(cases.getParam1());
                    return new JudgeResult(ProgrammingMsg.REJECT, cases, ProgrammingMsg.CASE_NOT_PASS);
                }
            }
            JudgeResult result = new JudgeResult();
            result.setStatus(ProgrammingMsg.ACCEPT);
            return result;
        }catch (Exception e){
            return new JudgeResult(ProgrammingMsg.RUNTIME_ERROR, null, e.getMessage());
        }
    }
}
