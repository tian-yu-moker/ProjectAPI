package com.hku.projectapi.Service.Interview;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.Collection.InterviewLike;
import com.hku.projectapi.Beans.Collection.KnowledgeLike;
import com.hku.projectapi.Beans.Interview.InterviewBean;
import com.hku.projectapi.Beans.Interview.InterviewCreateDTO;
import com.hku.projectapi.Beans.Interview.InterviewQueryDTO;
import com.hku.projectapi.Beans.Knowledge.KnowledgeAnswerBean;
import com.hku.projectapi.Beans.Knowledge.KnowledgeCommentsBean;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestion;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionBean;
import com.hku.projectapi.Beans.QueryByPageDTO;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Beans.User.UserBean;
import com.hku.projectapi.Controller.Interview.InterviewController;
import com.hku.projectapi.Mapper.Collection.InterviewCollectionMapper;
import com.hku.projectapi.Mapper.Interview.InterviewMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeAnswerMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeCommentMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeQuestionMapper;
import com.hku.projectapi.Mapper.Users.UserMapper;
import com.hku.projectapi.Service.Knowledge.KnowledgeService;
import com.hku.projectapi.Tools.JwtUtil;
import com.hku.projectapi.Tools.UUidGenerator;
import net.sf.jsqlparser.statement.select.KSQLWindow;
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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private KnowledgeAnswerMapper knowledgeAnswerMapper;
    @Autowired
    private KnowledgeCommentMapper knowledgeCommentMapper;
    @Autowired
    private KnowledgeService knowledgeService;
    @Autowired
    private InterviewCollectionMapper interviewCollectionMapper;

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

    // 加载Interview以及其Knowledge questions，以及其answer，格式统一
    public Result queryById(String interviewId, String userId)
    {
        String userName = this.getName(userId);
        try{
            QueryWrapper<InterviewBean> intQuery = new QueryWrapper<>();
            intQuery.eq("interview_id", interviewId);
            List<InterviewBean> interviews = interviewMapper.selectList(intQuery);
            if(interviews.size() == 1){
                InterviewBean one = interviews.get(0);
                QueryWrapper<KnowledgeQuestionBean> knowWrapper = new QueryWrapper<>();
                knowWrapper.eq("interview_id", interviewId);
                List<KnowledgeQuestionBean> questions = knowledgeQuestionMapper.selectList(knowWrapper);
                for(KnowledgeQuestionBean beans:questions){
                    QueryWrapper<KnowledgeAnswerBean> answersQuery = new QueryWrapper<>();
                    QueryWrapper<KnowledgeCommentsBean> commentsQuery = new QueryWrapper<>();
                    answersQuery.eq("knowledge_id", beans.getKnowledgeId());
                    commentsQuery.eq("knowledge_id", beans.getKnowledgeId());
                    List<KnowledgeAnswerBean> answers = knowledgeAnswerMapper.selectList(answersQuery);
                    List<KnowledgeCommentsBean> comments = knowledgeCommentMapper.selectList(commentsQuery);
                    for(KnowledgeAnswerBean ans:answers){
                        String email = ans.getProviderId();
                        ans.setUserName(this.getName(email));
                    }
                    for(KnowledgeCommentsBean com:comments){
                        String email = com.getProviderId();
                        com.setUserName(this.getName(email));
                    }
                    // Set is liked and user name
                    beans.setIsLiked(knowledgeService.getIsLike(userId, beans.getKnowledgeId()));
                    beans.setUserName(this.getName(userId));
                    beans.setAnswers(answers);
                    beans.setComments(comments);
                }
                InterviewQueryDTO result = new InterviewQueryDTO();
                //
                one.setProviderName(this.getName(userId));
                System.out.println(one.getProviderName() + " AAA");
                one.setIsLiked(this.getIsLiked(userId, interviewId));
                result.setInterview(one);
                result.setQuestions(questions);
                return new Result("00", "Success.", null, result);
            }else {
                return new Result("13", "No such interview, please check.", null);
            }
        } catch (Exception e){
            return new Result("99", "Internal service error.", null);
        }
    }

    // Get user name by user id.
    public String getName(String id)
    {
        QueryWrapper<UserBean> query = new QueryWrapper<>();
        query.eq("email", id);
        UserBean oneUser = userMapper.selectOne(query);
        if(oneUser.getName().equals(null)){
            return "Unknown user";
        }else {
            return oneUser.getName();
        }
    }

    public int getIsLiked(String userId, String interviewId)
    {
        QueryWrapper<InterviewLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userId).eq("interview_id", interviewId);
        List<InterviewLike> res = interviewCollectionMapper.selectList(queryWrapper);
        if(res.size() == 1)
        {
            return 1;
        }
        else
        {
            return 10;
        }
    }
}
