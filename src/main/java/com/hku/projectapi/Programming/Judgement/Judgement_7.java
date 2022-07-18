package com.hku.projectapi.Programming.Judgement;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hku.projectapi.Beans.Programming.JudgeResult;
import com.hku.projectapi.Beans.Programming.ProgrammingMsg;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Programming.AnswerCode.Answer_7;
import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

public class Judgement_7 extends BaseJudgement
{
    private Answer_7 answer_7;

    public Judgement_7(ProgrammingQuestionBean programInfo, String filePath, String uuid)
    {
        super(programInfo, filePath, uuid);
        this.answer_7 = new Answer_7();
    }

    public JudgeResult doJudge() {
        try {
            URL url = new URL(this.filePath);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});
            Class<?> cls = Class.forName(this.uuid, true, classLoader);
            Object instance = cls.newInstance();
            Method method = cls.getDeclaredMethod("reverse", String.class, int.class);
            method.setAccessible(true);
            List<GeneralBean> testCases = this.programInfo.getTestCases();

            for (GeneralBean cases : testCases) {
                String param1Obj = JSON.toJSONString(cases.getParam1());
                int param1 = JSONObject.parseObject(param1Obj, int.class);
                int resTruth = this.answer_7.reverse(param1);
                int resUser = (int) method.invoke(instance, param1);
                if (resTruth != resUser) {
                    GeneralBean failedCase = new GeneralBean();
                    failedCase.setParam1(cases.getParam1());
                    return new JudgeResult(ProgrammingMsg.REJECT, cases, ProgrammingMsg.CASE_NOT_PASS);
                }
            }
            // If all test cases have been passed, then return true
            JudgeResult result = new JudgeResult();
            result.setStatus(ProgrammingMsg.ACCEPT);
            return result;
        } catch (Exception e) {
            return new JudgeResult(ProgrammingMsg.RUNTIME_ERROR, null, e.getMessage());
        }
    }
}
