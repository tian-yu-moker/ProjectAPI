package com.hku.projectapi.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.KnowledgeQuestionBean;
import com.hku.projectapi.Beans.QueryByPageDTO;
import com.hku.projectapi.Beans.QueryInfo;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Mapper.KnowledgeQuestionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.List;

@Service
@SpringBootTest
public class KnowledgeService extends ServiceImpl<KnowledgeQuestionMapper, KnowledgeQuestionBean>
{
    @Autowired
    private KnowledgeQuestionMapper knowledgeQuestionMapper;

    @Test
    public void test()
    {
        Page<KnowledgeQuestionBean> resPage = knowledgeQuestionMapper.selectPage(new Page<>(1, 2), null);
        System.out.println(resPage.getTotal());
    }

    /**
     * Get all the records of the knowledge question
     * @param currentPage
     * @param pageSize
     * @param type by time or by hot level, default by time (0)
     */
    public Result searchByPage(int currentPage, int pageSize, int type)
    {
        try{
            if(type == 0)
            {
                QueryWrapper<KnowledgeQuestionBean> queryWrapper = new QueryWrapper<>();
                queryWrapper.select().orderByDesc("upload_time");
                Page<KnowledgeQuestionBean> resPage = knowledgeQuestionMapper.selectPage(new Page<>(currentPage, pageSize), queryWrapper);
                List<KnowledgeQuestionBean> records = resPage.getRecords();
                QueryByPageDTO queryDTO = new QueryByPageDTO();
                QueryInfo queryInfo = new QueryInfo();
                queryInfo.setPageSize(pageSize);
                queryInfo.setCurrentPage(currentPage);
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
