package com.hku.projectapi.Programming;

import com.hku.projectapi.Beans.Programming.JudgeResult;
import com.hku.projectapi.Beans.Programming.ProgrammingHistoryBean;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Programming.Judgement.TwoSumJudge;
import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

public class ExecutionHandler
{
    public static ProgrammingHistoryBean doExecution(ProgrammingQuestionBean programInfo, String language, String filePath, String uuid) {

//        System.out.println(language.equals("Java") + " 12131");
//        System.out.println(programInfo.getId() + " AAA");
        int questionId = programInfo.getId();
        ProgrammingHistoryBean result = new ProgrammingHistoryBean();
        List<GeneralBean> cases = new ArrayList<>();
        if(language.equals("Java"))
        {
            switch (questionId){
                case 1:
                    TwoSumJudge judge = new TwoSumJudge(programInfo, filePath, "a" + uuid);
                    JudgeResult judgeResult = judge.doJudge();
                    result.setStatus(judgeResult.getStatus());
                    result.setQuestionId(1);
                    cases.add(judgeResult.getFailedCase());
//                    result.setFailedCases(judgeResult.getFailedCase());
                    result.setFailedCases(cases);
                    result.setMessage(judgeResult.getMsg());
                    break;
                case 2:
                    System.out.println(2 + " AAA");
                case 3:
                    System.out.println(3 + " AAA");
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args)
    {
//        ExecutionHandler.doExecution(2);
    }
}
