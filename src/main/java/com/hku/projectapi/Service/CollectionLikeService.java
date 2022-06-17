package com.hku.projectapi.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hku.projectapi.Mapper.KnowledgeLikeMapper;

@Service
public class CollectionLikeService 
{
    @Autowired
    private KnowledgeLikeMapper knowledgeMapper;

    public void getKnowledge()
    {
        
    }
}
