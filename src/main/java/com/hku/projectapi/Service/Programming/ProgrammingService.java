package com.hku.projectapi.Service.Programming;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hku.projectapi.Beans.Programming.*;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Mapper.Programming.ProgrammingHistoryMapper;
import com.hku.projectapi.Mapper.Programming.ProgrammingQuestionMapper;
import com.hku.projectapi.Programming.JavaTaskThread;
import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;
import com.hku.projectapi.Service.Interview.InterviewService;
import com.hku.projectapi.Tools.UUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProgrammingService
{
    @Autowired
    private ProgrammingQuestionMapper programmingQuestionMapper;
    @Autowired
    private ProgrammingHistoryMapper programmingHistoryMapper;
    @Autowired
    private InterviewService interviewService;

    public void create(ProgrammingQuestionBean programmingQuestions)
    {
        //
        QueryWrapper<ProgrammingQuestionBean> programWrapper = new QueryWrapper<>();
        programmingQuestionMapper.insert(programmingQuestions);
    }

    public void test()
    {
        QueryWrapper<ProgrammingQuestionBean> programmingQuestionBeanQueryWrapper = new QueryWrapper<>();
        programmingQuestionBeanQueryWrapper.eq("id", 1);
        List<ProgrammingQuestionBean> res = programmingQuestionMapper.selectList(programmingQuestionBeanQueryWrapper);
        System.out.println(res.get(0).getTestCases().get(0).getParam2().toString());
    }

    /**
     * Do judgement
     * 1. Parse the uploaded code
     * 2. Do compilation
     * 3. Do execution (input test cases, get results and compare difference)
     * 4. Determine the
     */
    public Result upload(ProgrammingUploadDTO uploadDTO, String userId)
    {
        int questionId = uploadDTO.getQuestionId();
        String uploadCode = uploadDTO.getCodes();
        String language = uploadDTO.getLang();
        Date date = new Date();
        Timestamp uploadTime = new Timestamp(date.getTime());

        QueryWrapper<ProgrammingQuestionBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", questionId);
        ProgrammingQuestionBean question = programmingQuestionMapper.selectList(queryWrapper).get(0);
        List<GeneralBean> cases = question.getTestCases();
        List<GeneralBean> processedCases = new ArrayList<>();
        // Process the json read from the database
        for(Object oneCase:cases){
            String jsonObj = JSON.toJSONString(oneCase);
            GeneralBean testCase = JSONObject.parseObject(jsonObj, GeneralBean.class);
            processedCases.add(testCase);
        }
        question.setTestCases(processedCases);
        String uuid = UUidGenerator.getUUID32();
        // Do execution, start a thread
        JavaTaskThread taskThread = new JavaTaskThread(question, uploadCode, uuid, userId);
        Thread executeThread = new Thread(taskThread);
        executeThread.start();
        ProgramWaitDTO waitDTO = new ProgramWaitDTO();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ProgrammingHistoryBean history = new ProgrammingHistoryBean();
                try {
                    Thread.sleep(setWait(questionId));
                    // Write to the database.
                    String curStatus = taskThread.getProgrammingHistoryBean().getStatus();
                    if(curStatus.equals("Not finished.")){
                        history.setStatus(ProgrammingMsg.TIME_EXCEED);
                        history.setUserId(userId);
                        history.setUuid(uuid);
                        history.setQuestionId(questionId);
                        history.setUploadedCode(uploadCode);
                        history.setUploadTime(uploadTime);
                        // Kill the running thread
                        executeThread.interrupt();
                    }
                    else
                    {
                        history = taskThread.getProgrammingHistoryBean();
                    }
//                    System.out.println();
                    history.setUuid(uuid);
                    List<GeneralBean> failedCases = history.getFailedCases();
                    List<GeneralBean> actual = new ArrayList<>();
                    for(Object cases:failedCases){
                        String obj = JSON.toJSONString(cases);
                        GeneralBean one = JSONObject.parseObject(obj, GeneralBean.class);
                        actual.add(one);
                    }
                    history.setFailedCases(actual);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                programmingHistoryMapper.insert(history);
            }
        }).start();
        // After three
        waitDTO.setUuid(uuid);
        waitDTO.setWaitMinutesToRequest(this.setWait(questionId));
        return new Result("00", "Success.", null, waitDTO);
//        try {
//
//        } catch (Exception e){
//            return new Result("99", "Internal server error.", null);
//        }
    }

    // Write the record to history table
    public void writeHistory(ProgrammingHistoryBean historyBean)
    {
        programmingHistoryMapper.insert(historyBean);
    }

    public long setWait(int id)
    {
        long time = 1000;
        if(id == 1){
            return 2000;
        }
        return time;
    }

    // Search the specific programming history
    public Result searchHistoryById(String uuid)
    {

        try {
            QueryWrapper<ProgrammingHistoryBean> historyWrapper = new QueryWrapper<>();
            historyWrapper.eq("uuid", uuid);
            List<ProgrammingHistoryBean> res = programmingHistoryMapper.selectList(historyWrapper);
            if(res.size() == 1){
                ProgrammingHistoryBean history = res.get(0);
                return new Result("00", "Success.", null, history);
            }else{
                return new Result("14", "No such record.", null);
            }
        } catch (Exception e){
            return new Result("99", "Internal server error, with error <" + e.getMessage() + ">", null);
        }
    }

    public Result getAllHistory(String userId, int questionId)
    {
        try {
            QueryWrapper<ProgrammingHistoryBean> historyWrapper = new QueryWrapper<>();
            if(questionId != -1){
                historyWrapper.eq("userId", userId).eq("questionId", questionId);
            }else {
                historyWrapper.eq("userId", userId);
            }
            List<ProgrammingHistoryBean> records = programmingHistoryMapper.selectList(historyWrapper);
            String userName = interviewService.getName(userId);
            UserProgrammingHistoryDTO historyDTO = new UserProgrammingHistoryDTO(userId, userName, questionId, records);
            return new Result("00", "Success.", null, historyDTO);
        }catch (Exception e){
            return new Result("99", "Internal server error, with error <" + e.getMessage() + ">", null);
        }
    }
}
