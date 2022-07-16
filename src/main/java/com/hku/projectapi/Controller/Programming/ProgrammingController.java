package com.hku.projectapi.Controller.Programming;

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
    public void create(@RequestBody ProgrammingQuestionBean programmingQuestions)
    {
        System.out.println("12133");
        programmingService.create(programmingQuestions);
    }

    // Upload a question to be judged
    @PostMapping("uploads")
    public Result upload(@RequestBody ProgrammingUploadDTO uploadDTO, @RequestHeader String token)
    {
        String userId = "";
        try {
            userId = JwtUtil.getUserId(token);
            token = JwtUtil.updateToken(token);
            Result result = programmingService.upload(uploadDTO, userId);
            result.setToken(token);
            return result;
        }catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
    }

    @PostMapping("judgement")
    public Result doJudgement(@RequestBody ProgrammingUploadDTO uploadDTO, @RequestHeader String token)
    {
        String userId = "";
        try {
            userId = JwtUtil.getUserId(token);
            token = JwtUtil.updateToken(token);
            Result result = programmingService.upload(uploadDTO, userId);
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
