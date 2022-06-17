package com.hku.projectapi.Service;

import java.util.Date;

import static org.mockito.Mockito.description;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hku.projectapi.Beans.knowledge_like;
import com.hku.projectapi.Mapper.KnowledgeLikeMapper;

import com.hku.projectapi.Beans.Result;

@Service
public class CollectionLikeService 
{
    @Autowired
    private KnowledgeLikeMapper knowledgeMapper;

    /**
     * Get all collections of liked interviews and knowledge questions
     */
    public void getWholeCollection()
    {

    }


    public void getInterview()
    {

    }

    public void getKnowledge()
    {

    }

    /**
     * Create a liked knowledge question, if no record, create one, if yes, return error.
     * @param email
     * @param knowledgeId
     */
    public Object addLikeKnowledge(String email, String knowledgeId)
    {
        QueryWrapper<knowledge_like> oneQuery = new QueryWrapper<>();
        oneQuery.eq(!email.equals(null), "email", email).
        eq(!knowledgeId.equals(null), "knowledge_id", knowledgeId);
        List<knowledge_like> res = knowledgeMapper.selectList(oneQuery);
        if(res.size() != 0)
        {
            knowledge_like likeBean = new knowledge_like(email, knowledgeId, new Timestamp(new Date().getTime()));
            knowledgeMapper.insert(likeBean);
            // Result response = new Result("00", "Success", "");
            
            return 0;
        }
        return -1;
    }
}
