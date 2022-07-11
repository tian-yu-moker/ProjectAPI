package com.hku.projectapi.Service.Collection;

import java.util.ArrayList;
import java.util.Date;

import java.sql.Timestamp;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.Collection.CollectionQueryDTO;
import com.hku.projectapi.Beans.Collection.InterviewLike;
import com.hku.projectapi.Beans.Interview.InterviewBean;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionBean;
import com.hku.projectapi.Beans.QueryByPageDTO;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Mapper.Collection.InterviewCollectionMapper;
import com.hku.projectapi.Mapper.Collection.KnowledgeCollectionMapper;
import com.hku.projectapi.Mapper.Interview.InterviewMapper;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeQuestionMapper;
import com.hku.projectapi.Service.Interview.InterviewService;
import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hku.projectapi.Beans.Collection.KnowledgeLike;

@Service
@SpringBootTest
public class CollectionLikeService extends ServiceImpl<KnowledgeCollectionMapper, KnowledgeLike>
{
    @Autowired
    private KnowledgeCollectionMapper knowledgeMapper;
    @Autowired
    private KnowledgeQuestionMapper knowledgeQuestionMapper;
    @Autowired
    private InterviewCollectionMapper interviewCollectionMapper;
    @Autowired
    private InterviewMapper interviewMapper;
    @Autowired
    private InterviewService interviewService;



    public CollectionLikeService() {
    }

    /**
     * Get all collections of liked interviews and knowledge questions
     */
    public Result getWholeCollection(String token)
    {
        String userId = "";
        try{
            userId = JwtUtil.getUserId(token);
        } catch (Exception e){
            log.error(e.getMessage());
            return new Result("98", "Invalid token, please login.", null);
        }
        try{
            QueryWrapper<KnowledgeLike> knowledgeLike = new QueryWrapper<>();
            QueryWrapper<InterviewLike> interviewLike = new QueryWrapper<>();
            knowledgeLike.eq("email", userId);
            interviewLike.eq("email", userId);
            List<KnowledgeLike> knowList = knowledgeMapper.selectList(knowledgeLike);
            List<InterviewLike> interList = interviewCollectionMapper.selectList(interviewLike);
            CollectionQueryDTO res = new CollectionQueryDTO();
            List<KnowledgeQuestionBean> resKnow = new ArrayList<>();
            List<InterviewBean> resInter = new ArrayList<>();
            // Query two tables, and obtain all related records.;
            for(KnowledgeLike knows:knowList){
                String id = knows.getKnowledge_id();
                QueryWrapper<KnowledgeQuestionBean> query = new QueryWrapper<>();
                query.eq("knowledge_id", id);

                List<KnowledgeQuestionBean> rec = knowledgeQuestionMapper.selectList(query);
                if(rec.size() == 1){
                    rec.get(0).setUserName(interviewService.getName(userId));
                    rec.get(0).setIsLiked(1);
                    resKnow.add(rec.get(0));
                }
            }
            for(InterviewLike inter:interList){
                String id = inter.getInterviewId();
                QueryWrapper<InterviewBean> query = new QueryWrapper<>();
                query.eq("interview_id", id);
                List<InterviewBean> rec = interviewMapper.selectList(query);
                if(rec.size() == 1){
                    rec.get(0).setIsLiked(1);
                    rec.get(0).setProviderName(interviewService.getName(rec.get(0).getProviderId()));
                    resInter.add(rec.get(0));
                }
            }
            res.setInterviews(resInter);
            res.setKnowledge(resKnow);
            QueryByPageDTO result = new QueryByPageDTO();
            result.setEntities(res);
            return new Result("00", "Success.", null, result);
        } catch (Exception e){
            log.error(e.getMessage());
            return new Result("99", "Internal service error.", null);
        }
    }


    /**
     * Query knowledge collection
     * @param email
     * @param id: optional, if empty, get all
     */
    public void getKnowledge(String email, String id)
    {
        System.out.println(email + " " + id);
        QueryWrapper<KnowledgeLike> oneQuery = new QueryWrapper<>();
        oneQuery.select("knowledge_id", "add_date");
        oneQuery.eq("email", email);
        if(id != null)
        {
//            System.out.println("12345");
            oneQuery.eq("knowledge_id", id);
        }
        // 再去查表
        List<KnowledgeLike> id_date = knowledgeMapper.selectList(oneQuery);
        // 查knowledge的表
        List<String> knowledge_ids = new ArrayList<String>();
        for(KnowledgeLike record : id_date)
        {
            knowledge_ids.add(record.getKnowledge_id());
        }
        QueryWrapper<KnowledgeQuestionBean> userLikeKnowledgeQuery = new QueryWrapper<>();
        List<KnowledgeQuestionBean> userLikedKnowledge = knowledgeQuestionMapper.selectList(userLikeKnowledgeQuery);
        System.out.println(userLikedKnowledge);
    }

    /**
     * Create a liked knowledge question, if no record, create one, if yes, return error.
     * @param email
     * @param knowledgeId
     */
    public int addLikeKnowledge(String email, String knowledgeId)
    {
        QueryWrapper<KnowledgeQuestionBean> knowledge = new QueryWrapper<>();
        knowledge.eq("knowledge_id", knowledgeId);
        List<KnowledgeQuestionBean> records = knowledgeQuestionMapper.selectList(knowledge);
        if(records.size() == 0){
            return -2;
        }
        QueryWrapper<KnowledgeLike> oneQuery = new QueryWrapper<>();
        oneQuery.eq(!email.equals(null), "email", email).
        eq(!knowledgeId.equals(null), "knowledge_id", knowledgeId);
        List<KnowledgeLike> res = knowledgeMapper.selectList(oneQuery);
        // If no such record, then create one.
        if(res.size() == 0)
        {
            KnowledgeLike likeBean = new KnowledgeLike(email, knowledgeId, new Timestamp(new Date().getTime()));
            knowledgeMapper.insert(likeBean);
            return 0;
        }
        else
        {
            knowledgeMapper.delete(oneQuery);
            return -1;
        }
    }

    // Add the interview to collection
    public int addInterviewLike(String email, String interviewId)
    {
        QueryWrapper<InterviewBean> interview = new QueryWrapper<>();
        interview.eq("interview_id", interviewId);
        List<InterviewBean> record = interviewMapper.selectList(interview);
        if(record.size() == 0)
        {
            return -2;
        }
        QueryWrapper<InterviewLike> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!email.equals(null), "email", email).
                eq(!interviewId.equals(null), "interview_id", interviewId);
        List<InterviewLike> res = interviewCollectionMapper.selectList(queryWrapper);
        // If no such record, then create one.
        if(res.size() == 0)
        {
            InterviewLike interviewLike = new InterviewLike(email, interviewId, new Timestamp(new Date().getTime()));
            interviewCollectionMapper.insert(interviewLike);
            return 0;
        }
        else
        {
            interviewCollectionMapper.delete(queryWrapper);
            return -1;
        }
    }

//    @Test/\
    public void test()
    {
        String email = "123@qq.com";
        String knowledgeId = "4186f6f46bb94450ae1b3abe54517228";
        QueryWrapper<KnowledgeLike> oneQuery = new QueryWrapper<>();
//        oneQuery.eq("email", email).
//                eq("knowledge_id", knowledgeId);
        List<KnowledgeLike> res = knowledgeMapper.selectList(oneQuery);
//        if(res.size() == 0)
//        {
//            knowledge_like likeBean = new knowledge_like(email, knowledgeId, new Timestamp(new Date().getTime()));
//            knowledgeMapper.insert(likeBean);
//        }
        System.out.println(res + "AAA");

    }

    public void tests()
    {
        // For test
    }

//    public static void main(String[] args)
//    {
//        new CollectionLikeService().test();
//    }
}
