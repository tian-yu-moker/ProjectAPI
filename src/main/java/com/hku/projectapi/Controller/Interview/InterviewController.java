package com.hku.projectapi.Controller.Interview;

import com.hku.projectapi.Beans.Interview.InterviewCreateDTO;
import com.hku.projectapi.Beans.PageRequestDTO;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Service.Interview.InterviewService;
import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*","null"})
@RestController
@RequestMapping("/interview_service")
public class InterviewController
{
    @Autowired
    private InterviewService interviewService;

    @PostMapping("create")
    public Result create(@RequestHeader String token, @RequestBody InterviewCreateDTO interviewDTO)
    {
        try {
            Result res = interviewService.create(interviewDTO, token);
            if(res.getCode() == "00"){
                res.setToken(JwtUtil.updateToken(token));
                return res;
            }
            else{
                return res;
            }
        } catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
    }

    @GetMapping("query")
    public Result query(@RequestHeader String token, @RequestParam String interviewId)
    {
        String userId = "";
        try {
            userId = JwtUtil.getUserId(token);
            Result res = interviewService.queryById(interviewId, userId);
            res.setToken(JwtUtil.updateToken(token));
            return res;
        } catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
    }

    @PostMapping("load")
    public Result loadInterviews(@RequestHeader String token, @RequestBody PageRequestDTO pageDTO)
    {
        try {
            Result res = interviewService.loadByPage(pageDTO, token);
            res.setToken(JwtUtil.updateToken(token));
            return res;
        } catch (Exception e){
            return new Result("99", "Internal service error.", null);
        }
    }
}
