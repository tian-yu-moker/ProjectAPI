package com.hku.projectapi.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.KnowledgeAnswerBean;
import com.hku.projectapi.Beans.KnowledgeAnswerRequestDTO;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Mapper.KnowledgeAnswerMapper;
import com.hku.projectapi.Tools.UUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.sql.Timestamp;

@Service
public class KnowledgeAnswerService extends ServiceImpl<KnowledgeAnswerMapper, KnowledgeAnswerBean>
{
    @Autowired
    private KnowledgeAnswerMapper knowledgeAnswerMapper;
    // Insert an answer
    public Result create(KnowledgeAnswerRequestDTO requestDTO)
    {
        String uuid = UUidGenerator.getUUID32();
        KnowledgeAnswerBean newRecord = new KnowledgeAnswerBean();
        newRecord.setKnowledgeAnswerId(uuid);
        newRecord.setKnowledgeId(requestDTO.getKnowledgeId());
        newRecord.setAnswerProviderId(requestDTO.getAnswerProvider());
        newRecord.setContent(requestDTO.getContent());
        newRecord.setUploadTime(new Timestamp(new Date().getTime()));
        newRecord.setLastModifiedTime(newRecord.getUploadTime());
        knowledgeAnswerMapper.insert(newRecord);
        try{

            return new Result("00", "Success.", null);
        }catch (Exception e)
        {
            return new Result("99", "Internal server error.", null);
        }

    }

    public void modify()
    {

    }

    public void delete()
    {

    }

    public void searchByKnowledge(String id)
    {
        QueryWrapper<KnowledgeAnswerBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("knowledge_id", id);

    }

}
