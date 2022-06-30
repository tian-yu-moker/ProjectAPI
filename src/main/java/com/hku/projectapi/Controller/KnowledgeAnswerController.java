package com.hku.projectapi.Controller;

import com.hku.projectapi.Beans.KnowledgeAnswerRequestDTO;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Service.KnowledgeAnswerService;
import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*","null"})
@RestController
public class KnowledgeAnswerController
{
    @Autowired
    private KnowledgeAnswerService knowledgeAnswerService;

    @PostMapping("knowledge_answer_service")
    public Result create(@RequestHeader String token, @RequestBody KnowledgeAnswerRequestDTO requestDTO)
    {
        Result res = knowledgeAnswerService.create(requestDTO);
//        token = JwtUtil.updateToken(token);
        res.setToken(token);
        return res;
    }


}
