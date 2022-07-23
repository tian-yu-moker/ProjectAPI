package com.hku.projectapi.Service.Knowledge;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.*;
import com.hku.projectapi.Beans.Collection.KnowledgeLike;
import com.hku.projectapi.Beans.Knowledge.KnowledgeAnswerBean;
import com.hku.projectapi.Beans.Knowledge.KnowledgeCommentsBean;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionDTO;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionBean;
import com.hku.projectapi.Beans.User.UserBean;
import com.hku.projectapi.Mapper.Collection.KnowledgeCollectionMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeAnswerMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeCommentMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeQuestionMapper;
import com.hku.projectapi.Mapper.Users.UserMapper;
import com.hku.projectapi.Tools.JwtUtil;
import com.hku.projectapi.Tools.UUidGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@SpringBootTest
public class KnowledgeService extends ServiceImpl<KnowledgeQuestionMapper, KnowledgeQuestionBean>
{
    @Autowired
    private KnowledgeQuestionMapper knowledgeQuestionMapper;
    @Autowired
    private KnowledgeAnswerService knowledgeAnswerService;
    @Autowired
    private KnowledgeCommentService knowledgeCommentService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private KnowledgeCollectionMapper knowledgeCollectionMapper;
    @Autowired
    private KnowledgeAnswerMapper knowledgeAnswerMapper;
    @Autowired
    private KnowledgeCommentMapper knowledgeCommentMapper;

    @Test
    public void test()
    {
        Page<KnowledgeQuestionBean> resPage = knowledgeQuestionMapper.selectPage(new Page<>(1, 2), null);
        System.out.println(resPage.getTotal());
    }

    /**
     * Get all the records of the knowledge question
     * type: by time or by hot level (like), default by time (0)
     */
    public Result searchByPage(PageRequestDTO pageRequestDTO, String token)
    {
        String userId = "";
        try {
            userId = JwtUtil.getUserId(token);
        } catch (Exception e)
        {
            log.error(e.getMessage());
            return new Result("98", "Invalid token, please login.", null);
        }
        try{
            if(pageRequestDTO.getType() == 0 &&
                    (pageRequestDTO.getTag1().equals(null) && pageRequestDTO.getTag2().equals(null) || pageRequestDTO.getTag1().equals("") && pageRequestDTO.getTag2().equals("")))
            {
                System.out.println("123");
                QueryWrapper<KnowledgeQuestionBean> queryWrapper = new QueryWrapper<>();
                queryWrapper.select().orderByDesc("upload_time");
                Page<KnowledgeQuestionBean> resPage = knowledgeQuestionMapper.selectPage(new Page<>(pageRequestDTO.getPageFirst(),
                        pageRequestDTO.getPageSizeFirst()), queryWrapper);
                List<KnowledgeQuestionBean> records = resPage.getRecords();
                for(KnowledgeQuestionBean results:records)
                {
                    QueryWrapper<UserBean> query = new QueryWrapper<>();
                    query.eq("email", results.getUserid());
                    UserBean oneUser = userMapper.selectOne(query);
                    results.setUserName(oneUser.getName());
                    QueryByPageDTO answers = knowledgeAnswerService.searchByKnowledge(results.getKnowledgeId(),
                            pageRequestDTO.getPageSecond(), pageRequestDTO.getPageSizeSecond());
                    results.setAnswers(answers);
                    QueryByPageDTO comments = knowledgeCommentService.searchByKnowledge(results.getKnowledgeId(),
                            pageRequestDTO.getPageThird(), pageRequestDTO.getPageSizeThird());
                    results.setComments(comments);
                    // Get if it is collected by the user.
                    int isLiked = this.getIsLike(userId, results.getKnowledgeId());
                    results.setIsLiked(isLiked);
                }
                QueryByPageDTO queryDTO = new QueryByPageDTO();
                QueryInfo queryInfo = new QueryInfo();
                queryInfo.setPageSize(pageRequestDTO.getPageSizeFirst());
                queryInfo.setCurrentPage(pageRequestDTO.getPageFirst());
                queryInfo.setTotalRecord(resPage.getTotal());
                queryDTO.setQueryInfo(queryInfo);
                queryDTO.setEntities(records);
                Result result = new Result();
                result.setCode("00");
                result.setDescription("Success.");
                result.setData(queryDTO);
                return result;
            }
            else
            {
                System.out.println("AAA");
                String tag1 = pageRequestDTO.getTag1();
                String tag2 = pageRequestDTO.getTag2();
                // If two select requirements
                List<KnowledgeQuestionBean> res = knowledgeQuestionMapper.selectByTags(tag1, tag2);
                for(KnowledgeQuestionBean beans:res){
                    String id = beans.getKnowledgeId();
                    System.out.println(id + " AAA");
                    QueryWrapper<UserBean> query = new QueryWrapper<>();
                    query.eq("email", beans.getUserid());
                    UserBean oneUser = userMapper.selectOne(query);
                    beans.setUserName(oneUser.getName());
                    QueryByPageDTO answers = knowledgeAnswerService.searchByKnowledge(beans.getKnowledgeId(),
                            pageRequestDTO.getPageSecond(), pageRequestDTO.getPageSizeSecond());
                    beans.setAnswers(answers);
                    QueryByPageDTO comments = knowledgeCommentService.searchByKnowledge(beans.getKnowledgeId(),
                            pageRequestDTO.getPageThird(), pageRequestDTO.getPageSizeThird());
                    beans.setComments(comments);
                    int isLiked = this.getIsLike(userId, beans.getKnowledgeId());
                    beans.setIsLiked(isLiked);
                }
                QueryByPageDTO queryDTO = new QueryByPageDTO();
                QueryInfo queryInfo = new QueryInfo();
                queryInfo.setPageSize(pageRequestDTO.getPageSizeFirst());
                queryInfo.setCurrentPage(pageRequestDTO.getPageFirst());
                queryInfo.setTotalRecord(res.size());
                queryDTO.setQueryInfo(queryInfo);
                queryDTO.setEntities(res);
                Result result = new Result();
                result.setCode("00");
                result.setDescription("Success.");
                result.setData(queryDTO);
                return result;
            }
        } catch (Exception e)
        {
            log.error(e.getMessage());
            return new Result("99", "Internal error, with the error <" + e.getMessage() + ">", null);
        }
    }

    // 创建questions
    public void create()
    {

    }

    // Determine whether the question is in the collection
    public int getIsLike(String userId, String knowledgeId)
    {
        QueryWrapper<KnowledgeLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userId).eq("knowledge_id", knowledgeId);
        List<KnowledgeLike> res = knowledgeCollectionMapper.selectList(queryWrapper);
        if(res.size() == 1)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public Result create(KnowledgeQuestionDTO question, String userId)
    {
        try{

        }catch (Exception e){
            log.error(e.getMessage());
            return new Result("99", "Internal server error.", null);
        }
        String userName = this.getName(userId);
        String uuid = UUidGenerator.getUUID32();
        String questionContent = question.getQuestion_content();
        String interviewId = question.getInterview_id();
        String company = question.getCompany();
        String tag = question.getTag();
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        KnowledgeQuestionBean oneQuestion = new KnowledgeQuestionBean();
        oneQuestion.setKnowledgeId(uuid);
        oneQuestion.setCompany(company);
        oneQuestion.setTag(tag);
        oneQuestion.setUserid(userId);
        oneQuestion.setUploadTime(time);
        oneQuestion.setInterviewId(interviewId);
        oneQuestion.setUserName(userName);
        oneQuestion.setQuestion_content(questionContent);
        knowledgeQuestionMapper.insert(oneQuestion);
        return new Result("00", "Success.", null);
    }

    public Result getKnowledgeById(String userId, String knowledgeId)
    {

        QueryWrapper<KnowledgeQuestionBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("knowledge_id", knowledgeId);
        List<KnowledgeQuestionBean> res = knowledgeQuestionMapper.selectList(queryWrapper);

        if(res.size() == 1)
        {
            QueryWrapper<KnowledgeAnswerBean> answersQuery = new QueryWrapper<>();
            QueryWrapper<KnowledgeCommentsBean> commentsQuery = new QueryWrapper<>();
            answersQuery.eq("knowledge_id", res.get(0).getKnowledgeId());
            commentsQuery.eq("knowledge_id", res.get(0).getKnowledgeId());
            List<KnowledgeAnswerBean> answers = knowledgeAnswerMapper.selectList(answersQuery);
            List<KnowledgeCommentsBean> comments = knowledgeCommentMapper.selectList(commentsQuery);
            for(KnowledgeAnswerBean beans:answers){
                String email = beans.getProviderId();
                beans.setUserName(this.getName(email));
            }
            for(KnowledgeCommentsBean beans:comments){
                String email = beans.getProviderId();
                beans.setUserName(this.getName(email));
            }
            QueryByPageDTO answerDTO = new QueryByPageDTO();
            answerDTO.setEntities(answers);
            QueryInfo ansInfo = new QueryInfo();
            ansInfo.setTotalRecord(answers.size());
            answerDTO.setQueryInfo(ansInfo);
            QueryByPageDTO commentsDTO = new QueryByPageDTO();
            QueryInfo commentInfo = new QueryInfo();
            commentInfo.setTotalRecord(comments.size());
            commentsDTO.setQueryInfo(commentInfo);
            commentsDTO.setEntities(comments);
            String name = this.getName(res.get(0).getUserid());
            res.get(0).setUserName(name);
            res.get(0).setAnswers(answerDTO);
            res.get(0).setComments(commentsDTO);
            res.get(0).setIsLiked(this.getIsLike(userId, knowledgeId));
            return new Result("00","Success.", "", res.get(0));
        }
        else
        {
            return new Result("05","No such knowledge question.", null);
        }
    }

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
}
