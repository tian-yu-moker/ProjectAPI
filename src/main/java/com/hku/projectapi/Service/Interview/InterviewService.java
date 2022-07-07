package com.hku.projectapi.Service.Interview;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.Interview.InterviewBean;
import com.hku.projectapi.Beans.Interview.InterviewCreateDTO;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestion;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionBean;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Mapper.Interview.InterviewMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeQuestionMapper;
import com.hku.projectapi.Tools.JwtUtil;
import com.hku.projectapi.Tools.UUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class InterviewService extends ServiceImpl<InterviewMapper, InterviewBean>
{
    @Autowired
    private InterviewMapper interviewMapper;
    @Autowired
    private KnowledgeQuestionMapper knowledgeQuestionMapper;

    // Create an interview, together with a list of questions
    public Result create(InterviewCreateDTO interviewDTO, String token)
    {
        String userId = JwtUtil.getUserId(token);
        if(userId == null){
            return new Result("97", "Invalid token, please login.", null);
        }
        else {
            try{
                String uuid = UUidGenerator.getUUID32();
                String title = interviewDTO.getTitle();
                String description = interviewDTO.getDescription();
                String company = interviewDTO.getCompany();
                List<KnowledgeQuestion> questions = interviewDTO.getQuestions();
                Date date = new Date();
                Timestamp uploadTime = new Timestamp(date.getTime());
                // 循环插入相关问题
                for (KnowledgeQuestion question : questions){
                    QueryWrapper<KnowledgeQuestionBean> queryWrapper = new QueryWrapper<>();
                    KnowledgeQuestionBean oneQuestion = new KnowledgeQuestionBean();
                    // 设置Interview id
                    oneQuestion.setInterviewId(uuid);
                    // 设置Knowledge id
                    oneQuestion.setKnowledgeId(UUidGenerator.getUUID32());
                    // 设置上传时间
                    oneQuestion.setUploadTime(uploadTime);
                    // 设置公司
                    oneQuestion.setCompany(company);
                    // 设置Tag
                    oneQuestion.setTag(question.getTag());
                    // 设置内容
                    oneQuestion.setQuestion_content(question.getQuestion_content());
                    // 设置answer和comment list
                    oneQuestion.setAnswer_list(null);
                    oneQuestion.setComment_list(null);
                    // 设置用户id
                    oneQuestion.setUserid(userId);
                    knowledgeQuestionMapper.insert(oneQuestion);
                }
                // 插入Interview
                InterviewBean interview = new InterviewBean();
                interview.setInterviewId(uuid);
                interview.setCompany(company);
                interview.setTitle(title);
                interview.setDescription(description);
                interview.setProviderId(userId);
                interview.setUploadTime(uploadTime);
                interviewMapper.insert(interview);
                return new Result("00", "Success.", null);
            } catch (Exception e){
                log.error(e.getMessage());
                return new Result("99", "Internal service error.", null);
            }
        }
    }

    // 加载Interview以及其Knowledge questions，以及其answer
    public void queryById(String id)
    {

    }
}
