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
    @PostMapping("upload")
    public Result upload(@RequestBody ProgrammingUploadDTO uploadDTO, @RequestHeader String token)
    {
        String userId = "";
        try {
            userId = JwtUtil.getUserId(token);
        }catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
        programmingService.upload(uploadDTO, userId);
        return new Result();
    }
}
