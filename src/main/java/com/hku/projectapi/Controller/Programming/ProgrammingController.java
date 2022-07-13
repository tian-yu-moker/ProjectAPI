package com.hku.projectapi.Controller.Programming;

import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import com.hku.projectapi.Service.Programming.ProgrammingService;
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
        System.out.println(programmingQuestions.getPostCode() + " AAA");
        programmingService.create(programmingQuestions);
    }
}
