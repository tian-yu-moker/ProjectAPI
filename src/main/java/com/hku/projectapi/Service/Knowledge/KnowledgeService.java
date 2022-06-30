package com.hku.projectapi.Service.Knowledge;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.*;
import com.hku.projectapi.Mapper.KnowledgeQuestionMapper;
import com.hku.projectapi.Service.Knowledge.KnowledgeAnswerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

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

    @Test
    public void test()
    {
        Page<KnowledgeQuestionBean> resPage = knowledgeQuestionMapper.selectPage(new Page<>(1, 2), null);
        System.out.println(resPage.getTotal());
    }

    /**
     * Get all the records of the knowledge question
     * type: by time or by hot level, default by time (0)
     */
    public Result searchByPage(PageRequestDTO pageRequestDTO)
    {
        try{
            if(pageRequestDTO.getType() == 0)
            {
                QueryWrapper<KnowledgeQuestionBean> queryWrapper = new QueryWrapper<>();
                queryWrapper.select().orderByDesc("upload_time");
                Page<KnowledgeQuestionBean> resPage = knowledgeQuestionMapper.selectPage(new Page<>(pageRequestDTO.getPageFirst(),
                        pageRequestDTO.getPageSizeFirst()), queryWrapper);
                List<KnowledgeQuestionBean> records = resPage.getRecords();
                for(KnowledgeQuestionBean results:records)
                {
                    QueryByPageDTO answers = knowledgeAnswerService.searchByKnowledge(results.getKnowledgeId(),
                            pageRequestDTO.getPageSecond(), pageRequestDTO.getPageSizeSecond());
                    results.setAnswers(answers);
                    QueryByPageDTO comments = knowledgeCommentService.searchByKnowledge(results.getKnowledgeId(),
                            pageRequestDTO.getPageThird(), pageRequestDTO.getPageSizeThird());
                    results.setComments(comments);
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
                return new Result();
            }
        } catch (Exception e)
        {
            return new Result("99", "Internal error.", null);
        }
    }

}
