package com.hku.projectapi.Controller.Knowledge;

import com.hku.projectapi.Beans.Knowledge.KnowledgeAnswerCommentRequestDTO;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Service.Knowledge.KnowledgeCommentService;
import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*","null"})
@RestController
@RequestMapping("/knowledge_service")
public class KnowledgeCommentController
{
    @Autowired
    private KnowledgeCommentService knowledgeCommentService;

    @PostMapping("comment")
    public Result create(@RequestHeader String token, @RequestBody KnowledgeAnswerCommentRequestDTO requestDTO)
    {
        try{
            Result res = knowledgeCommentService.create(requestDTO, token);
            token = JwtUtil.updateToken(token);
            res.setToken(token);
            return res;
        } catch (Exception e){
            return new Result("98", "Invalid token, please login.", null);
        }
    }
}
