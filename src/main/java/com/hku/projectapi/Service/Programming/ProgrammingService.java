package com.hku.projectapi.Service.Programming;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hku.projectapi.Beans.PageRequestDTO;
import com.hku.projectapi.Beans.Programming.*;
import com.hku.projectapi.Beans.QueryByPageDTO;
import com.hku.projectapi.Beans.QueryInfo;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Mapper.Programming.ProgrammingHistoryMapper;
import com.hku.projectapi.Mapper.Programming.ProgrammingIsPassedMapper;
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
    @Autowired
    private ProgrammingIsPassedMapper programmingIsPassedMapper;

    public Result create(ProgrammingQuestionBean programmingQuestions)
    {
        //
//        QueryWrapper<ProgrammingQuestionBean> programWrapper = new QueryWrapper<>();
        try {
            programmingQuestionMapper.insert(programmingQuestions);
            return new Result("00", "Success.", null);
        }catch (Exception e){
            return new Result("99", "Internal server error, with error <" + e.getMessage() + ">", null);
        }
    }

    public void test()
    {
        QueryWrapper<ProgrammingQuestionBean> programmingQuestionBeanQueryWrapper = new QueryWrapper<>();
        programmingQuestionBeanQueryWrapper.eq("id", 1);
        List<ProgrammingQuestionBean> res = programmingQuestionMapper.selectList(programmingQuestionBeanQueryWrapper);
        System.out.println(res.get(0).getTestCases().get(0).getParam2().toString());
    }

    public Result getQuestionsByPage(PageRequestDTO requestDTO, String userId)
    {
        try {
            int curPage= requestDTO.getPageFirst();
            int pageSize = requestDTO.getPageSizeFirst();
            QueryWrapper<ProgrammingQuestionBean> questionWrapper = new QueryWrapper<>();
            questionWrapper.orderByAsc("id");
            Page<ProgrammingQuestionBean> resPage = programmingQuestionMapper.selectPage(new Page<>(curPage, pageSize), questionWrapper);
            List<ProgrammingQuestionBean> records = resPage.getRecords();
            for(ProgrammingQuestionBean beans:records){
                QueryWrapper<ProgramIsPassed> isPassedQueryWrapper = new QueryWrapper<>();
                isPassedQueryWrapper.eq("userId", userId).eq("questionId", beans.getId());
                List<ProgramIsPassed> res = programmingIsPassedMapper.selectList(isPassedQueryWrapper);
                if(res.size() == 1){
                    beans.setIsPassed(res.get(0).getIsPassed());
                }else {
                    beans.setIsPassed(0);
                }
            }
            QueryByPageDTO dto = new QueryByPageDTO();
            QueryInfo queryInfo = new QueryInfo();
            queryInfo.setCurrentPage(curPage);
            queryInfo.setPageSize(pageSize);
            queryInfo.setTotalRecord(resPage.getTotal());
            dto.setQueryInfo(queryInfo);
            dto.setEntities(records);
            return new Result("00", "Success.", null, dto);
        }catch (Exception e){
            return new Result("99", "Internal server error, with error <" + e.getMessage() + ">", null);
        }
    }

    /**
     * Do judgement
     * 1. Parse the uploaded code
     * 2. Do compilation
     * 3. Do execution (input test cases, get results and compare difference)
     * 4. Determine the
     */
    public Result judgement(ProgrammingUploadDTO uploadDTO, String userId)
    {
        try {
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
                    history.setUuid(uuid);
                    try {
                        Thread.sleep(setWait(questionId));
                        // Write to the database.
                        String curStatus = taskThread.getProgrammingHistoryBean().getStatus();
                        if(curStatus.equals("Not finished.")){
                            history.setStatus(ProgrammingMsg.TIME_EXCEED);
                            history.setUserId(userId);
                            history.setQuestionId(questionId);
                            history.setUploadedCode(uploadCode);
                            history.setUploadTime(uploadTime);
                            // Kill the running thread
                            executeThread.interrupt();
                        }
                        else if(curStatus.equals(ProgrammingMsg.REJECT))
                        {
                            history = taskThread.getProgrammingHistoryBean();
                            List<GeneralBean> failedCases = history.getFailedCases();
                            List<GeneralBean> actual = new ArrayList<>();
                            for(Object cases:failedCases){
                                String obj = JSON.toJSONString(cases);
                                GeneralBean one = JSONObject.parseObject(obj, GeneralBean.class);
                                actual.add(one);
                            }
                            history.setFailedCases(actual);
                        }
                        else{
                            history = taskThread.getProgrammingHistoryBean();
                            history.setFailedCases(null);
                        }
                        history.setUuid(uuid);
                        // Set history
                        ProgramIsPassed isPassed = new ProgramIsPassed();
                        isPassed.setUserId(userId);
                        isPassed.setQuestionId(questionId);
                        QueryWrapper<ProgramIsPassed> isPassedQueryWrapper = new QueryWrapper<>();
                        isPassedQueryWrapper.eq("userId", userId).eq("questionId", questionId);
                        List<ProgramIsPassed> res = programmingIsPassedMapper.selectList(isPassedQueryWrapper);
                        if(res.size() == 0){
                            if(history.getStatus().equals(ProgrammingMsg.ACCEPT)){
                                isPassed.setIsPassed(1);
                            }else {
                                isPassed.setIsPassed(2);
                            }
                            programmingIsPassedMapper.insert(isPassed);
                        }else {
                            if(res.get(0).getIsPassed() != 1 && history.getStatus().equals("Accept")){
                                isPassed.setIsPassed(1);
                                programmingIsPassedMapper.update(isPassed, isPassedQueryWrapper);
                            }else{
                                isPassed.setIsPassed(2);
                                programmingIsPassedMapper.update(isPassed, isPassedQueryWrapper);
                            }

                        }
                        programmingHistoryMapper.insert(history);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            // After three
            waitDTO.setUuid(uuid);
            waitDTO.setWaitMinutesToRequest(this.setWait(questionId));
            return new Result("00", "Success.", null, waitDTO);
        } catch (Exception e){
            return new Result("99", "Internal server error.", null);
        }
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
        else if(id == 2){
            return 1500;
        }
        else if(id == 3){
            return 1500;
        }else if(id == 4){
            return 2000;
        }else if(id == 5){
            return 2000;
        }else if(id == 6){
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
