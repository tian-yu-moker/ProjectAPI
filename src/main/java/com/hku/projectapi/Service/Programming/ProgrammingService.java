package com.hku.projectapi.Service.Programming;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Mapper.Programming.ProgrammingQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgrammingService
{
    @Autowired
    private ProgrammingQuestionMapper programmingQuestionMapper;

    public void create(ProgrammingQuestionBean programmingQuestions)
    {
        //
        QueryWrapper<ProgrammingQuestionBean> programWrapper = new QueryWrapper<>();
        programmingQuestionMapper.insert(programmingQuestions);
    }

    /**
     * Do judgement
     * 1. Parse the uploaded code
     * 2. Do compilation
     * 3. Do execution (input test cases, get results and compare difference)
     * 4. Determine the
     */
    public void upload()
    {

    }
}
