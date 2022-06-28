package com.hku.projectapi.Service;

import java.util.ArrayList;
import java.util.Date;

import static org.mockito.Mockito.description;

import java.sql.Timestamp;
import java.util.List;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.KnowledgeQuestion;
import com.hku.projectapi.Beans.KnowledgeQuestionBean;
import com.hku.projectapi.Mapper.KnowledgeCollectionMapper;
import com.hku.projectapi.Mapper.KnowledgeQuestionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hku.projectapi.Beans.knowledge_like;
import com.hku.projectapi.Mapper.KnowledgeLikeMapper;

import com.hku.projectapi.Beans.Result;

import javax.naming.ldap.PagedResultsControl;

@Service
@SpringBootTest
public class CollectionLikeService extends ServiceImpl<KnowledgeCollectionMapper, knowledge_like>
{
    @Autowired
    private KnowledgeCollectionMapper knowledgeMapper;
    @Autowired
    private KnowledgeQuestionMapper knowledgeQuestionMapper;



    public CollectionLikeService() {
    }

    /**
     * Get all collections of liked interviews and knowledge questions
     */
    public void getWholeCollection()
    {

    }


    public void getInterview()
    {

    }

    /**
     * Query knowledge collection
     * @param email
     * @param id: optional, if empty, get all
     */
    public void getKnowledge(String email, String id)
    {
        System.out.println(email + " " + id);
        QueryWrapper<knowledge_like> oneQuery = new QueryWrapper<>();
        oneQuery.select("knowledge_id", "add_date");
        oneQuery.eq("email", email);
        if(id != null)
        {
//            System.out.println("12345");
            oneQuery.eq("knowledge_id", id);
        }
        // 再去查表
        List<knowledge_like> id_date = knowledgeMapper.selectList(oneQuery);
        // 查knowledge的表
        List<String> knowledge_ids = new ArrayList<String>();
        for(knowledge_like record : id_date)
        {
            knowledge_ids.add(record.getKnowledge_id());
        }
        QueryWrapper<KnowledgeQuestionBean> userLikeKnowledgeQuery = new QueryWrapper<>();
        List<KnowledgeQuestionBean> userLikedKnowledge = knowledgeQuestionMapper.selectList(userLikeKnowledgeQuery);
        System.out.println(userLikedKnowledge);

//        System.out.println(res);
    }

    /**
     * Create a liked knowledge question, if no record, create one, if yes, return error.
     * @param email
     * @param knowledgeId
     */
    public int addLikeKnowledge(String email, String knowledgeId)
    {
        QueryWrapper<knowledge_like> oneQuery = new QueryWrapper<>();
        oneQuery.eq(!email.equals(null), "email", email).
        eq(!knowledgeId.equals(null), "knowledge_id", knowledgeId);
        List<knowledge_like> res = knowledgeMapper.selectList(oneQuery);
        // If no such record, then create one.
        if(res.size() == 0)
        {
            knowledge_like likeBean = new knowledge_like(email, knowledgeId, new Timestamp(new Date().getTime()));
            knowledgeMapper.insert(likeBean);
            return 0;
        }
        return -1;
    }

//    @Test/\
    public void test()
    {
        String email = "123@qq.com";
        String knowledgeId = "4186f6f46bb94450ae1b3abe54517228";
        QueryWrapper<knowledge_like> oneQuery = new QueryWrapper<>();
//        oneQuery.eq("email", email).
//                eq("knowledge_id", knowledgeId);
        List<knowledge_like> res = knowledgeMapper.selectList(oneQuery);
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
