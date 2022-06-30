package com.hku.projectapi.Service.Knowledge;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.Knowledge.KnowledgeCommentsBean;
import com.hku.projectapi.Beans.Knowledge.KnowledgeAnswerCommentRequestDTO;
import com.hku.projectapi.Beans.QueryByPageDTO;
import com.hku.projectapi.Beans.QueryInfo;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Mapper.Knowledge.KnowledgeCommentMapper;
import com.hku.projectapi.Tools.UUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class KnowledgeCommentService extends ServiceImpl<KnowledgeCommentMapper, KnowledgeCommentsBean>
{
    @Autowired
    private KnowledgeCommentMapper knowledgeCommentMapper;

    public Result create(KnowledgeAnswerCommentRequestDTO requestDTO)
    {
        try{
            String uuid = UUidGenerator.getUUID32();
            KnowledgeCommentsBean newRecord = new KnowledgeCommentsBean();
            newRecord.setKnowledgeCommentId(uuid);
            newRecord.setKnowledgeId(requestDTO.getKnowledgeId());
            newRecord.setProviderId(requestDTO.getProvider());
            newRecord.setContent(requestDTO.getContent());
            newRecord.setUploadTime(new Timestamp(new Date().getTime()));
            newRecord.setLastModifiedTime(newRecord.getUploadTime());
            knowledgeCommentMapper.insert(newRecord);
            return new Result("00", "Success.", null);
        }catch(Exception e)
        {
            return new Result("99", "Internal server error.", null);
        }
    }

    /**
     * Search the comments by id, page select
     * @param id
     * @param commentCurPage
     * @param commentCurPageSize
     * @return
     */
    public QueryByPageDTO searchByKnowledge(String id, int commentCurPage, int commentCurPageSize)
    {
        QueryWrapper<KnowledgeCommentsBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("knowledge_id", id);
        Page<KnowledgeCommentsBean> resPage = knowledgeCommentMapper.selectPage(new Page<>(commentCurPage, commentCurPageSize),
                queryWrapper);
        List<KnowledgeCommentsBean> records = resPage.getRecords();
        QueryByPageDTO queryDTO = new QueryByPageDTO();
        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setCurrentPage(commentCurPage);
        queryInfo.setPageSize(commentCurPageSize);
        queryInfo.setTotalRecord(resPage.getTotal());
        queryDTO.setQueryInfo(queryInfo);
        queryDTO.setEntities(records);
        return queryDTO;
    }

}
