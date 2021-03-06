package com.hku.projectapi.Programming.Judgement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hku.projectapi.Beans.Programming.JudgeResult;
import com.hku.projectapi.Beans.Programming.ProgrammingHistoryBean;
import com.hku.projectapi.Beans.Programming.ProgrammingMsg;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Programming.AnswerCode.TwoSumAnswer_1;
import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.List;

public class TwoSumJudge_1
{
    private TwoSumAnswer_1 answerCode;
    private String filePath;
    private ProgrammingQuestionBean programInfo;
    private String uuid;

    public TwoSumJudge_1(ProgrammingQuestionBean programInfo, String filePath, String uuid)
    {
        answerCode = new TwoSumAnswer_1();
        this.programInfo = programInfo;
        this.filePath = filePath;
        this.uuid = uuid;
    }

    public JudgeResult doJudge()
    {
//        System.out.println(this.filePath + " filePath");

        try {
            URL url = new URL(this.filePath);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});
            Class<?> cls = Class.forName(this.uuid, true, classLoader);
            Object instance = cls.newInstance();
            Method method = cls.getDeclaredMethod("twoSum", int[].class, int.class);
            method.setAccessible(true);
            List<GeneralBean> testCases = this.programInfo.getTestCases();
            // Loop, to judge each test case
            for(GeneralBean cases:testCases){
                // Get the test cases and feed into the methods
                String param1Obj = JSON.toJSONString(cases.getParam1());
                int[] param1 = JSONObject.parseObject(param1Obj, int[].class);
                int param2 = (int) cases.getParam2();
                // Do compare
                int resTruth[] = this.answerCode.twoSum(param1, param2);
                int resUser[] = (int[]) method.invoke(instance, param1, param2);
//            System.out.println(Arrays.equals(resTruth, resUser));
                // If the current test case does not pass. then break.
                if(!Arrays.equals(resTruth, resUser)){
                    GeneralBean failedCase = new GeneralBean();
                    failedCase.setParam1(cases.getParam1());
                    failedCase.setParam2(cases.getParam2());
                    return new JudgeResult(ProgrammingMsg.REJECT, cases, ProgrammingMsg.CASE_NOT_PASS);
                }
            }
            // If all test cases have been passed, then return true
            JudgeResult result = new JudgeResult();
            result.setStatus(ProgrammingMsg.ACCEPT);
            return result;
        }catch (Exception e){
            return new JudgeResult(ProgrammingMsg.RUNTIME_ERROR, null, e.getMessage());
        }
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
