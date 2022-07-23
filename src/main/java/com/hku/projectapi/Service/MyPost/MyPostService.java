package com.hku.projectapi.Service.MyPost;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hku.projectapi.Beans.Interview.InterviewBean;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionBean;
import com.hku.projectapi.Beans.MyPost.MyPostDTO;
import com.hku.projectapi.Beans.QueryByPageDTO;
import com.hku.projectapi.Beans.QueryInfo;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Mapper.Interview.InterviewMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeAnswerMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeCommentMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeQuestionMapper;
import com.hku.projectapi.Service.Interview.InterviewService;
import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyPostService
{
    @Autowired
    private InterviewMapper interviewMapper;
    @Autowired
    private KnowledgeQuestionMapper knowledgeQuestionMapper;
    @Autowired
    private KnowledgeAnswerMapper knowledgeAnswerMapper;
    @Autowired
    private KnowledgeCommentMapper knowledgeCommentMapper;
    @Autowired
    private InterviewService interviewService;

    // Load all posted interviews and questions by the user id
    public Result load(String token)
    {
        String userId = "";
        try{
            userId = JwtUtil.getUserId(token);
        } catch (Exception e){

        }
        try{
            QueryWrapper<InterviewBean> interWrapper = new QueryWrapper<>();
            QueryWrapper<KnowledgeQuestionBean> knowWrapper = new QueryWrapper<>();
            interWrapper.eq("providerId", userId);
            interWrapper.orderByDesc("upload_time");
            List<InterviewBean> interviews = interviewMapper.selectList(interWrapper);
            for(InterviewBean beans:interviews){
                beans.setUserName(interviewService.getName(userId));
            }
            knowWrapper.eq("userid", userId);
            knowWrapper.orderByDesc("upload_time");
            List<KnowledgeQuestionBean> questions = knowledgeQuestionMapper.selectList(knowWrapper);
            for(KnowledgeQuestionBean beans:questions){
                beans.setUserName(interviewService.getName(userId));
            }

            QueryInfo interInfo = new QueryInfo();
            interInfo.setTotalRecord(interviews.size());
            QueryInfo quesInfo = new QueryInfo();
            quesInfo.setTotalRecord(questions.size());

            QueryByPageDTO resInter = new QueryByPageDTO();
            resInter.setQueryInfo(interInfo);
            resInter.setEntities(interviews);

            QueryByPageDTO resQues = new QueryByPageDTO();
            resQues.setQueryInfo(quesInfo);
            resQues.setEntities(questions);

            MyPostDTO result = new MyPostDTO();
            result.setInterviews(resInter);
            result.setQuestions(resQues);
            return new Result("00", "Success.", null, result);
        } catch (Exception e){
            return new Result("99", "Internal server error.", null);
        }
    }
}
