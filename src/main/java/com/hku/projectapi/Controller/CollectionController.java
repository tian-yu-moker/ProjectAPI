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
     * @param type optional, knowledge, interview questions or whole interview
     * @param id optional, id of three of them
     * @return
     */
    @GetMapping("/users_like")
    public Object getAllLike(// @RequestHeader("token") String token,
                             @RequestParam(value = "email", required = true) String email,
                             @RequestParam(value = "type", required = true) int type,
                             @RequestParam(value = "id", required = false) String id)
    {
        // If search all collections for one user
        if(type == 0)
        {
            System.out.println("whole");
        }
        else
        {
            if(type == 1)
            {
                System.out.println("Knowledge");
            }
            else if(type == 2)
            {
                System.out.println("interview questions");
            }
            else
            {
                System.out.println("Whole interview");
            }
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
