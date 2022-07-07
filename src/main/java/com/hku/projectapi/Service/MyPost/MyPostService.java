package com.hku.projectapi.Service.MyPost;

import com.hku.projectapi.Mapper.Interview.InterviewMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeQuestionMapper;
import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyPostService
{
    @Autowired
    private InterviewMapper interviewMapper;
    @Autowired
    private KnowledgeQuestionMapper knowledgeQuestionMapper;

    // Load all posted interviews and knowledge by the user id
    public void load(String token)
    {
        String userId = "";
        try{
            userId = JwtUtil.getUserId(token);
        } catch (Exception e){

        }
        try{

        } catch (Exception e){

        }

    }
}
