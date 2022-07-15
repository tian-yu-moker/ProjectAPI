package com.hku.projectapi.Programming.Judgement;

import com.hku.projectapi.Beans.Programming.JudgeResult;
import com.hku.projectapi.Beans.Programming.ProgrammingHistoryBean;
import com.hku.projectapi.Beans.Programming.ProgrammingMsg;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Programming.AnswerCode.TwoSumAnswer;
import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwoSumJudge
{
    private TwoSumAnswer answerCode;
    private String filePath;
    private ProgrammingQuestionBean programInfo;
    private String solution = "Solution";

    public TwoSumJudge(ProgrammingQuestionBean programInfo, String filePath)
    {
        answerCode = new TwoSumAnswer();
        this.programInfo = programInfo;
        this.filePath = filePath;
    }

    public JudgeResult doJudge() throws Exception
    {
        System.out.println(this.filePath + " filePath");
        URL url = new URL(this.filePath);
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});

        Class<?> cls = Class.forName(this.solution, true, classLoader);
        Object instance = cls.newInstance();
        Method method = cls.getDeclaredMethod("twoSum", int[].class, int.class);
        method.setAccessible(true);
        List<GeneralBean> testCases = this.programInfo.getTestCases();
        // Loop, to judge each test case
        for(GeneralBean cases:testCases){
            int[] param1 = (int[]) cases.getParam1();
            int param2 = (int) cases.getParam2();
            // Do comparing
            int resTruth[] = this.answerCode.twoSum(param1, param2);
            int resUser[] = (int[]) method.invoke(instance, param1, param2);
            // If the current test case does not pass. then break.
            if(!Arrays.equals(resTruth, resUser)){
                GeneralBean failedCase = new GeneralBean();
                failedCase.setParam1(cases.getParam1());
                failedCase.setParam2(cases.getParam2());
                return new JudgeResult("Reject", failedCase, ProgrammingMsg.CASE_NOT_PASS);
            }
        }
        // If all test cases have been passed, then return true
        JudgeResult result = new JudgeResult();
        result.setStatus("Accept");
        return result;
//        try {
//
//        }catch (Exception e){
//            return new JudgeResult("Error", null, e.getMessage());
//        }
    }

    public ProgrammingHistoryBean setRes(boolean isSuccess)
    {
        ProgrammingHistoryBean result = new ProgrammingHistoryBean();
        if(isSuccess){
            result.setQuestionId(1);
//            result.setUuid();
        }else {

        }
        return result;
    }

    public static void main(String[] args)
    {
        int a[] = new int[]{1,2,3};
        int b[] = new int[]{1,2,3};
        System.out.println(Arrays.equals(a, b));
    }
}
