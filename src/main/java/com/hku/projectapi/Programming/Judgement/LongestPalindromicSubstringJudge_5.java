package com.hku.projectapi.Programming.Judgement;

import com.hku.projectapi.Beans.Programming.JudgeResult;
import com.hku.projectapi.Beans.Programming.ProgrammingMsg;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Programming.AnswerCode.LongestPalindromicSubstringAnswer_5;
import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class LongestPalindromicSubstringJudge_5 extends BaseJudgement
{
    private LongestPalindromicSubstringAnswer_5 longestPalindromicSubstringAnswer5;

    public LongestPalindromicSubstringJudge_5(ProgrammingQuestionBean programInfo, String filePath, String uuid)
    {
        super(programInfo, filePath, uuid);
        this.longestPalindromicSubstringAnswer5 = new LongestPalindromicSubstringAnswer_5();
    }
    public JudgeResult doJudge() {
        try {
            URL url = new URL(this.filePath);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});
            Class<?> cls = Class.forName(this.uuid, true, classLoader);
            Object instance = cls.newInstance();

            Method method = cls.getDeclaredMethod("longestPalindrome", String.class);
            method.setAccessible(true);
            List<GeneralBean> testCases = this.programInfo.getTestCases();

            for(GeneralBean cases:testCases){
                String param1 = (String) cases.getParam1();
                String resTruth = longestPalindromicSubstringAnswer5.longestPalindrome(param1);
                String resUser = (String) method.invoke(instance, param1);
                if(!resTruth.equals(resUser)){
                    GeneralBean failedCase = new GeneralBean();
                    failedCase.setParam1(cases.getParam1());
                    return new JudgeResult(ProgrammingMsg.REJECT, cases, ProgrammingMsg.CASE_NOT_PASS);
                }
            }
            JudgeResult result = new JudgeResult();
            result.setStatus(ProgrammingMsg.ACCEPT);
            return result;
        } catch (Exception e) {
            return new JudgeResult(ProgrammingMsg.RUNTIME_ERROR, null, e.getMessage());
        }
    }
}
