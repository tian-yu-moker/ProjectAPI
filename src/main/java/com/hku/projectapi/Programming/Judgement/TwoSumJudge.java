package com.hku.projectapi.Programming.Judgement;

import com.hku.projectapi.Programming.AnswerCode.TwoSumAnswer;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.net.URLClassLoader;

@Component
public class TwoSumJudge
{
    private TwoSumAnswer answerCode;

    public void controller()
    {

//        URL url = new URL("");
    }

    //
    public void doJudge(URL url, String filePath)
    {
        URL url = new URL("");
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});
    }
}
