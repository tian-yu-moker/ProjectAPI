package com.hku.projectapi.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hku.projectapi.Beans.CollectLikeDTO;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Service.CollectionLikeService;

@CrossOrigin(origins = {"*","null"})
@RestController
public class CollectionController 
{
    @Autowired
    private CollectionLikeService collectionService;

    /**
     * Add a liked question
     * @Type 0: knowledge; 1: interview
     * @return
     */
    @PostMapping("/users_like")
    public Object addLike(@RequestBody CollectLikeDTO dto, @RequestHeader("token") String token)
    {
         try{
             if(dto.getType() == 0)
             {
                 int resCode = collectionService.addLikeKnowledge(dto.getEmail(), dto.getKnowledge_id());
                 if(resCode == 0)
                 {
                     return new Result<Object>("00", "Success.", token, null);
                 }
                 else
                 {
                     return new Result<Object>("09", "Already liked such question.", token, null);
                 }
             }
             // For interview questions
             else
             {

             }
         } catch(Exception exception)
         {
             return new Result<Object>("99", "Internal service error.", token, null);
         }
        String res = "";
        return res;
    }

    /**
     *
     * @param email users' email
     * @param knowledge_id optional, knowledge id
     * @param inter_ques_id optional, interview question id
     * @param interview_id optional, interview id
     * @return
     */
    @GetMapping("/users_like")
    public Object getAllLike(// @RequestHeader("token") String token,
                             @RequestParam(value = "email", required = false) String email,
                             @RequestParam(value = "knowledge_id", required = false) String knowledge_id,
                             @RequestParam(value = "inter_ques_id", required = false) String inter_ques_id,
                             @RequestParam(value = "interview_id", required = false) String interview_id)
    {
        System.out.println(email + " " + knowledge_id + " " + inter_ques_id);
        // If search all collections for one user
        if(knowledge_id.equals(null) && inter_ques_id.equals(null) && interview_id.equals(null))
        {
            System.out.println("all");
        }
        // Search for knowledge
        else if(!knowledge_id.equals(null) && inter_ques_id.equals(null) && interview_id.equals(null))
        {
            System.out.println("knowledge");
        }
        // Search for interview question
        else if(knowledge_id.equals(null) && !inter_ques_id.equals(null) && interview_id.equals(null))
        {
            System.out.println("interview question");
        }
        // Search for whole interview
        else if(knowledge_id.equals(null) && inter_ques_id.equals(null) && !interview_id.equals(null))
        {
            System.out.println("interview");
        }
        String result = "Get request.";
        return result;
    }



    @DeleteMapping("/users_like")
    public Object cancelLike()
    {
        String res = "";
        return res;
    }


}
