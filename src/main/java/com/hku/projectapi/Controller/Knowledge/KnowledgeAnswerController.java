package com.hku.projectapi.Controller.Knowledge;

import com.hku.projectapi.Beans.Knowledge.KnowledgeAnswerCommentRequestDTO;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Service.Knowledge.KnowledgeAnswerService;
import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*","null"})
@RestController
@RequestMapping("/knowledge_service")
public class KnowledgeAnswerController
{
    @Autowired
    private KnowledgeAnswerService knowledgeAnswerService;

    @PostMapping("answer")
    public Result create(@RequestHeader String token, @RequestBody KnowledgeAnswerCommentRequestDTO requestDTO)
    {
        Result res = knowledgeAnswerService.create(requestDTO);
//        token = JwtUtil.updateToken(token);
        res.setToken(token);
        return res;
    }

    // Should verify whether this answer is provided by user
    @DeleteMapping("answer")
    public Result delete(@RequestHeader String token, @RequestParam(value = "id", required = true) String id)
    {

        try{
            Result result = knowledgeAnswerService.delete(id, token);
            token = JwtUtil.updateToken(token);
            result.setToken(token);
            return result;
        } catch (Exception e)
        {
            return new Result("97", "Token invalid, permission denied.", null);
        }
    }

}
