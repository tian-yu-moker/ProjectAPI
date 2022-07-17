package com.hku.projectapi.Programming.Judgement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hku.projectapi.Beans.Programming.JudgeResult;
import com.hku.projectapi.Beans.Programming.ProgrammingMsg;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Programming.AnswerCode.ZigTagConversionAnswer_6;
import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class Judgement_6 extends BaseJudgement
{
    private ZigTagConversionAnswer_6 zigTagConversionAnswer_6;

    public Judgement_6(ProgrammingQuestionBean programInfo, String filePath, String uuid)
    {
        super(programInfo, filePath, uuid);
        this.zigTagConversionAnswer_6 = new ZigTagConversionAnswer_6();
    }

    public JudgeResult doJudge() {
        try {
            URL url = new URL(this.filePath);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});
            Class<?> cls = Class.forName(this.uuid, true, classLoader);
            Object instance = cls.newInstance();

            Method method = cls.getDeclaredMethod("convert", String.class, int.class);
            method.setAccessible(true);
            List<GeneralBean> testCases = this.programInfo.getTestCases();

            for (GeneralBean cases : testCases) {
                String param1 = (String) cases.getParam1();
                String param2Obj = JSON.toJSONString(cases.getParam2());
                int param2 = JSONObject.parseObject(param2Obj, int.class);
                String resTruth = this.zigTagConversionAnswer_6.convert(param1, param2);
                String resUser = (String) method.invoke(instance, param1, param2);
                if (!resTruth.equals(resUser)) {
                    GeneralBean failedCase = new GeneralBean();
                    failedCase.setParam1(cases.getParam1());
                    failedCase.setParam2(cases.getParam2());
                    return new JudgeResult("Reject", cases, ProgrammingMsg.CASE_NOT_PASS);
                }
            }
            // If all test cases have been passed, then return true
            JudgeResult result = new JudgeResult();
            result.setStatus("Accept");
            return result;
        } catch (Exception e) {
            return new JudgeResult(ProgrammingMsg.RUNTIME_ERROR, null, e.getMessage());
        }
    }
}
