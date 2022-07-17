package com.hku.projectapi.Programming;

import com.hku.projectapi.Beans.Programming.JudgeResult;
import com.hku.projectapi.Beans.Programming.ProgrammingHistoryBean;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Programming.AnswerCode.MedianOfTwoSortedArraysAnswer;
import com.hku.projectapi.Programming.Judgement.*;
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
        String className = "a" + uuid;
        if(language.equals("Java"))
        {
            switch (questionId){
                case 1:
                    TwoSumJudge judge1 = new TwoSumJudge(programInfo, filePath, "a" + uuid);
                    JudgeResult judgeResult1 = judge1.doJudge();
                    result.setStatus(judgeResult1.getStatus());
                    result.setQuestionId(1);
                    cases.add(judgeResult1.getFailedCase());
                    result.setFailedCases(cases);
                    result.setMessage(judgeResult1.getMsg());
                    break;
                case 2:
                    StringReverseJudge judge2 = new StringReverseJudge(programInfo, filePath, "a" + uuid);
                    JudgeResult judgeResult2 = judge2.doJudge();
                    result.setStatus(judgeResult2.getStatus());
                    result.setQuestionId(questionId);
                    cases.add(judgeResult2.getFailedCase());
                    result.setFailedCases(cases);
                    result.setMessage(judgeResult2.getMsg());
                    break;
                case 3:
                    LengthOfNoRepeatSubstringJudge judge3 = new LengthOfNoRepeatSubstringJudge(programInfo, filePath, className);
                    JudgeResult judgeResult3 = judge3.doJudge();
                    result.setStatus(judgeResult3.getStatus());
                    result.setQuestionId(questionId);
                    cases.add(judgeResult3.getFailedCase());
                    result.setFailedCases(cases);
                    result.setMessage(judgeResult3.getMsg());
                    break;
                case 4:
                    MedianOfTwoSortedArrayJudge judge4 = new MedianOfTwoSortedArrayJudge(programInfo, filePath, className);
                    JudgeResult judgeResult4 = judge4.doJudge();
                    result.setStatus(judgeResult4.getStatus());
                    result.setQuestionId(questionId);
                    cases.add(judgeResult4.getFailedCase());
                    result.setFailedCases(cases);
                    result.setMessage(judgeResult4.getMsg());
                    break;
                case 5:
                    LongestPalindromicSubstringJudge judge5 = new LongestPalindromicSubstringJudge(programInfo, filePath, className);
                    JudgeResult judgeResult5 = judge5.doJudge();
                    result.setStatus(judgeResult5.getStatus());
                    result.setQuestionId(questionId);
                    cases.add(judgeResult5.getFailedCase());
                    result.setFailedCases(cases);
                    result.setMessage(judgeResult5.getMsg());
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
