package com.hku.projectapi.Programming;

import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Programming.Judgement.TwoSumJudge;
import lombok.extern.slf4j.Slf4j;

public class ExecutionHandler
{
    public static void doExecution(ProgrammingQuestionBean programInfo, String language, String filePath) throws Exception {

//        System.out.println(language.equals("Java") + " 12131");
//        System.out.println(programInfo.getId() + " AAA");
        int questionId = programInfo.getId();
        if(language.equals("Java"))
        {
            switch (questionId){
                case 1:
                    TwoSumJudge judge = new TwoSumJudge(programInfo, filePath);
                    System.out.println(judge.doJudge().getStatus());
                    break;
                case 2:
                    System.out.println(2 + " AAA");
                case 3:
                    System.out.println(3 + " AAA");
                    break;
            }
        }
    }

    public static void main(String[] args)
    {
//        ExecutionHandler.doExecution(2);
    }
}
