package com.hku.projectapi.Service.Knowledge;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.*;
import com.hku.projectapi.Beans.Knowledge.KnowledgeAnswerBean;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeAnswerMapper;
import com.hku.projectapi.Tools.UUidGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Service
@SpringBootTest
public class KnowledgeAnswerService extends ServiceImpl<KnowledgeAnswerMapper, KnowledgeAnswerBean>
{
    @Autowired
    private KnowledgeAnswerMapper knowledgeAnswerMapper;
    // Insert an answer
    public Result create(KnowledgeAnswerCommentRequestDTO requestDTO)
    {
        String uuid = UUidGenerator.getUUID32();
        KnowledgeAnswerBean newRecord = new KnowledgeAnswerBean();
        newRecord.setKnowledgeAnswerId(uuid);
        newRecord.setKnowledgeId(requestDTO.getKnowledgeId());
        newRecord.setProviderId(requestDTO.getProvider());
        newRecord.setContent(requestDTO.getContent());
        newRecord.setUploadTime(new Timestamp(new Date().getTime()));
        newRecord.setLastModifiedTime(newRecord.getUploadTime());
        try{
            knowledgeAnswerMapper.insert(newRecord);
            return new Result("00", "Success.", null);
        }catch (Exception e)
        {
            return new Result("99", "Internal server error.", null);
        }

    }

    public void modify()
    {

    }

    public Result delete(String id)
    {
        try{
            HashMap<String, Object> map = new HashMap<>();
            map.put("knowledge_answer_id", id);
            int res = knowledgeAnswerMapper.deleteByMap(map);
            System.out.println(res);
            if(res == 1)
            {
                return new Result("00", "Success.", null);
            }
            else
            {
                return new Result("09", "Delete error.",  null);
            }
        } catch (Exception e)
        {
            return new Result("99", "Internal server error.", null);
        }
    }

    // 分页搜索
    public QueryByPageDTO searchByKnowledge(String id, int answerCurPage, int answerCurPageSize)
    {
        QueryWrapper<KnowledgeAnswerBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("knowledge_id", id);
        Page<KnowledgeAnswerBean> resPage = knowledgeAnswerMapper.selectPage(new Page<>(answerCurPage, answerCurPageSize), queryWrapper);
        List<KnowledgeAnswerBean> records = resPage.getRecords();
        QueryByPageDTO queryDTO = new QueryByPageDTO();
        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setCurrentPage(answerCurPage);
        queryInfo.setPageSize(answerCurPageSize);
        queryInfo.setTotalRecord(resPage.getTotal());
        queryDTO.setQueryInfo(queryInfo);
        queryDTO.setEntities(records);
        return queryDTO;
    }

}