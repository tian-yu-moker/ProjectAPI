package com.hku.projectapi.Service.Interview;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.Collection.InterviewLike;
import com.hku.projectapi.Beans.Interview.InterviewBean;
import com.hku.projectapi.Beans.Interview.InterviewCreateDTO;
import com.hku.projectapi.Beans.Interview.InterviewQueryDTO;
import com.hku.projectapi.Beans.Knowledge.KnowledgeAnswerBean;
import com.hku.projectapi.Beans.Knowledge.KnowledgeCommentsBean;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionDTO;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionBean;
import com.hku.projectapi.Beans.PageRequestDTO;
import com.hku.projectapi.Beans.QueryByPageDTO;
import com.hku.projectapi.Beans.QueryInfo;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Beans.User.UserBean;
import com.hku.projectapi.Mapper.Collection.InterviewCollectionMapper;
import com.hku.projectapi.Mapper.Interview.InterviewMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeAnswerMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeCommentMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeQuestionMapper;
import com.hku.projectapi.Mapper.Users.UserMapper;
import com.hku.projectapi.Service.Knowledge.KnowledgeService;
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
                List<KnowledgeQuestionDTO> questions = interviewDTO.getQuestions();
                Date date = new Date();
                Timestamp uploadTime = new Timestamp(date.getTime());
                // 循环插入相关问题
                for (KnowledgeQuestionDTO question : questions){
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
                interview.setUserId(userId);
                interview.setUploadTime(uploadTime);
                // Newly added
                interview.setLevel(interviewDTO.getLevel());
                interview.setLocation(interviewDTO.getLocation());
                interview.setInterviewTime(interviewDTO.getInterview_time());
                interview.setPosition(interviewDTO.getPosition());
                System.out.println(interview.getLevel());
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

                    QueryByPageDTO answerPage = new QueryByPageDTO();
                    QueryByPageDTO commentPage = new QueryByPageDTO();
                    QueryInfo answerInfo = new QueryInfo();
                    QueryInfo commentsInfo = new QueryInfo();
                    answerInfo.setTotalRecord(answers.size());
                    commentsInfo.setTotalRecord(comments.size());
                    // Set answers
                    answerPage.setQueryInfo(answerInfo);
                    answerPage.setEntities(answers);
                    // Set comments
                    commentPage.setQueryInfo(commentsInfo);
                    commentPage.setEntities(comments);
                    // Set all
                    beans.setAnswers(answerPage);
                    beans.setComments(commentPage);
                }
                InterviewQueryDTO result = new InterviewQueryDTO();
                one.setUserName(this.getName(userId));
                one.setIsLiked(this.getIsLiked(userId, interviewId));

                QueryByPageDTO resultPage = new QueryByPageDTO();
                QueryInfo queryInfoQues = new QueryInfo();
                queryInfoQues.setTotalRecord(questions.size());
                resultPage.setQueryInfo(queryInfoQues);
                resultPage.setEntities(questions);

                result.setInterview(one);
                result.setQuestions(resultPage);
                return new Result("00", "Success.", null, result);
            }else {
                return new Result("13", "No such interview, please check.", null);
            }
        } catch (Exception e){
            return new Result("99", "Internal service error.", null);
        }
    }

    public Result loadByPage(PageRequestDTO pageRequestDTO, String token)
    {
        String userId = "";
        try {
            userId = JwtUtil.getUserId(token);
        } catch (Exception e)
        {
            log.error(e.getMessage());
            return new Result("98", "Invalid token, please login.", null);
        }
        // Current page and page size
        int curPage= pageRequestDTO.getPageFirst();
        int pageSize = pageRequestDTO.getPageSizeFirst();
        QueryWrapper<InterviewBean> interWrapper = new QueryWrapper<>();
        interWrapper.select().orderByDesc("upload_time");
        Page<InterviewBean> resPage = interviewMapper.selectPage(new Page<>(curPage, pageSize), interWrapper);
        List<InterviewBean> records = resPage.getRecords();

        for(InterviewBean beans:records){
            beans.setIsLiked(this.getIsLiked(userId, beans.getInterviewId()));
            beans.setUserName(this.getName(userId));
            QueryWrapper<KnowledgeQuestionBean> knowWrapper = new QueryWrapper<>();
            knowWrapper.eq("interview_id", beans.getInterviewId());
            List<KnowledgeQuestionBean> questions = knowledgeQuestionMapper.selectList(knowWrapper);
            QueryByPageDTO knowledgeQuestions = new QueryByPageDTO();
            QueryInfo knowledgeInfo = new QueryInfo();
            knowledgeInfo.setTotalRecord(questions.size());
            for(KnowledgeQuestionBean beansQuestion:questions){
//                beansQuestion.setIsLiked(knowledgeService.getIsLike(userId, beansQuestion.getKnowledgeId()));
                beansQuestion.setUserName(this.getName(userId));
            }
            knowledgeQuestions.setQueryInfo(knowledgeInfo);
            knowledgeQuestions.setEntities(questions);
            beans.setQuestions(knowledgeQuestions);
        }
        QueryByPageDTO result = new QueryByPageDTO();
        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setPageSize(pageRequestDTO.getPageSizeFirst());
        queryInfo.setCurrentPage(pageRequestDTO.getPageFirst());
        queryInfo.setTotalRecord(resPage.getTotal());
        result.setQueryInfo(queryInfo);
        result.setEntities(records);
        return new Result("00", "Success.", null, result);
//        try{
//
//        }catch(Exception e){
//            return new Result("99", "Internal service error.", null);
//        }
    }

    //
    public void loadByPage()
    {

    }

    // Get username by user id.
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
            return 0;
        }
    }
}
