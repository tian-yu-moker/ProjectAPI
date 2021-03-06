package com.hku.projectapi.Controller.Programming;

import com.hku.projectapi.Beans.PageRequestDTO;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Beans.Programming.ProgrammingUploadDTO;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Service.Programming.ProgrammingService;
import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*","null"})
@RestController
@RequestMapping("/programming_service")
public class ProgrammingController
{
    @Autowired
    private ProgrammingService programmingService;

    @PostMapping("create")
    public Result create(@RequestHeader String token, @RequestBody ProgrammingQuestionBean programmingQuestions)
    {
        try {
            Result res = programmingService.create(programmingQuestions);
            res.setToken(JwtUtil.updateToken(token));
            return res;
        }catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
    }

    // Get the question list by page
    @PostMapping("get_questions")
    public Result getQuestionList(@RequestHeader String token, @RequestBody PageRequestDTO requestDTO)
    {
        String userId = "";
        String updatedToken = "";
        try {
            userId = JwtUtil.getUserId(token);
            updatedToken = JwtUtil.updateToken(token);
        }catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
        try {
            Result result = programmingService.getQuestionsByPage(requestDTO, userId);
            result.setToken(updatedToken);
            return result;
        }catch (Exception e){
            return new Result("99", "Internal server error, with error msg <" + e.getMessage() + ">", updatedToken);
        }
    }

    // Upload a question to be judged
    @PostMapping("uploads")
    public Result upload(@RequestBody ProgrammingUploadDTO uploadDTO, @RequestHeader String token)
    {
        try {
            String userId = JwtUtil.getUserId(token);
            token = JwtUtil.updateToken(token);
            Result result = programmingService.judgement(uploadDTO, userId);
            result.setToken(token);
            return result;
        }catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
    }

    @PostMapping("judgement")
    public Result doJudgement(@RequestBody ProgrammingUploadDTO uploadDTO, @RequestHeader String token)
    {
        try{
            String userId = JwtUtil.getUserId(token);
            token = JwtUtil.updateToken(token);
            Result result = programmingService.judgement(uploadDTO, userId);
            result.setToken(token);
            return result;
        }catch (Exception e) {
            return new Result("97", "Invalid token, please login.", null);
        }
    }

    @GetMapping("one_history")
    public Result getHistoryById(@RequestHeader String token, @RequestParam String uuid)
    {
        String updatedToken = "";
        try{
            updatedToken = JwtUtil.updateToken(token);
        }catch (Exception e)
        {
            return new Result("97", "Invalid token, please login.", null);
        }
        try {
            Result result = programmingService.searchHistoryById(uuid);
            result.setToken(updatedToken);
            return result;
        }catch (Exception e){
            return new Result("99", "Internal server error, with error msg <" + e.getMessage() + ">", updatedToken);
        }
    }

    @GetMapping("all_history")
    public Result getAllUserHistory(@RequestHeader String token, @RequestParam(value = "questionId", required = false, defaultValue = "-1") String questionId)
    {
        String userId = "";
        try {
            userId = JwtUtil.getUserId(token);
            int qId = Integer.parseInt(questionId);
            Result result = programmingService.getAllHistory(userId, qId);
            result.setToken(JwtUtil.updateToken(token));
            return result;
        }catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
    }

    @GetMapping("test")
    public void test()
    {
        programmingService.test();
    }
}
