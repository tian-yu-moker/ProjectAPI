package com.hku.projectapi.Programming.Judgement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hku.projectapi.Beans.Programming.JudgeResult;
import com.hku.projectapi.Beans.Programming.ProgrammingMsg;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Programming.AnswerCode.MedianOfTwoSortedArraysAnswer_4;
import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class MedianOfTwoSortedArrayJudge_4
{
    private MedianOfTwoSortedArraysAnswer_4 medianOfTwoSortedArraysAnswer4;
    private String filePath;
    private ProgrammingQuestionBean programInfo;
    private String uuid;

    public MedianOfTwoSortedArrayJudge_4(ProgrammingQuestionBean programInfo, String filePath, String uuid)
    {
        this.medianOfTwoSortedArraysAnswer4 = new MedianOfTwoSortedArraysAnswer_4();
        this.programInfo = programInfo;
        this.filePath = filePath;
        this.uuid = uuid;
    }

    public JudgeResult doJudge() {
        try {
            URL url = new URL(this.filePath);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});
            Class<?> cls = Class.forName(this.uuid, true, classLoader);
            Object instance = cls.newInstance();
            Method method = cls.getDeclaredMethod("findMedianSortedArrays", int[].class, int[].class);
            method.setAccessible(true);
            List<GeneralBean> testCases = this.programInfo.getTestCases();

            for(GeneralBean cases:testCases){
                String param1Obj = JSON.toJSONString(cases.getParam1());
                int[] param1 = JSONObject.parseObject(param1Obj, int[].class);
                String param2Obj = JSON.toJSONString(cases.getParam2());
                int[] param2 = JSONObject.parseObject(param1Obj, int[].class);
                double resTruth = this.medianOfTwoSortedArraysAnswer4.findMedianSortedArrays(param1, param2);
                double resUser = (double) method.invoke(instance, param1, param2);
                if(resTruth != resUser){
                    GeneralBean failedCase = new GeneralBean();
                    failedCase.setParam1(cases.getParam1());
                    failedCase.setParam2(cases.getParam2());
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
