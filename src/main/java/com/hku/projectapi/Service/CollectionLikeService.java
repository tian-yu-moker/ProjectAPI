package com.hku.projectapi.Service;

import java.util.Date;

import static org.mockito.Mockito.description;

import java.sql.Timestamp;
import java.util.List;

import com.hku.projectapi.Mapper.KnowledgeCollectionMapper;
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
public class CollectionLikeService 
{
    @Autowired
    private KnowledgeCollectionMapper knowledgeMapper;
    @Autowired
    private KnowledgeLikeMapper knowledgeLikeMapper;

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
//        oneQuery.select("knowledge_id", "add_date");
//        oneQuery.eq("email", email);
        if(id != null)
        {
//            System.out.println("12345");
            oneQuery.eq("knowledge_id", id);
        }
//        QueryWrapper<knowledge_like> oneQuery = new QueryWrapper<>();
//        oneQuery.eq(!email.equals(null), "email", email);
        List<knowledge_like> res = knowledgeMapper.selectList(oneQuery);
        // List<knowledge_like> res = knowledgeMapper.selectList(oneQuery);

        String emails = "123@qq.com";
        String knowledgeId = "4186f6f46bb94450ae1b3abe54517228";
        QueryWrapper<knowledge_like> oneQuerys = new QueryWrapper<>();
        oneQuery.eq("email", emails).
                eq("knowledge_id", knowledgeId);
        List<knowledge_like> ress = knowledgeMapper.selectList(oneQuerys);
        System.out.println(ress + " aaa");
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

    @Test
    public void test()
    {
        String email = "123@qq.com";
        String knowledgeId = "4186f6f46bb94450ae1b3abe54517228";
        QueryWrapper<knowledge_like> oneQuery = new QueryWrapper<>();
        oneQuery.eq("email", email).
                eq("knowledge_id", knowledgeId);
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
