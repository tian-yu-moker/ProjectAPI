package com.hku.projectapi.Service.Programming;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hku.projectapi.Beans.Programming.ProgrammingHistoryBean;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Beans.Programming.ProgrammingUploadDTO;
import com.hku.projectapi.Mapper.Programming.ProgrammingHistoryMapper;
import com.hku.projectapi.Mapper.Programming.ProgrammingQuestionMapper;
import com.hku.projectapi.Programming.ExecutionHandler;
import com.hku.projectapi.Programming.JavaTaskThread;
import com.hku.projectapi.Tools.UUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgrammingService
{
    @Autowired
    private ProgrammingQuestionMapper programmingQuestionMapper;
    @Autowired
    private ProgrammingHistoryMapper programmingHistoryMapper;

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
    public void upload(ProgrammingUploadDTO uploadDTO, String userId)
    {
        int questionId = uploadDTO.getQuestionId();
        String uploadCode = uploadDTO.getCodes();
        String language = uploadDTO.getLang();

        QueryWrapper<ProgrammingQuestionBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", questionId);
        ProgrammingQuestionBean question = programmingQuestionMapper.selectList(queryWrapper).get(0);
        System.out.println(question.getId() + " AAA ");
        // Do execution, start a thread
        JavaTaskThread taskThread = new JavaTaskThread(question, uploadCode, UUidGenerator.getUUID32(), userId);
        new Thread(taskThread).start();
//        System.out.println(questionId + " AAA " + uploadCode);
    }

    public void writeHistory(ProgrammingHistoryBean historyBean)
    {
        programmingHistoryMapper.insert(historyBean);
    }
}
