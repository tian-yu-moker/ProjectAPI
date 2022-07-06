package com.hku.projectapi.Controller.Interview;

import com.hku.projectapi.Beans.Interview.InterviewCreateDTO;
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
}
