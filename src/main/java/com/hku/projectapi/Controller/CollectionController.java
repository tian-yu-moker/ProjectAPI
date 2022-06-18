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
//    @Autowired
//    private CollectionLikeService collectionService;

    /**
     * Add a liked question
     * @Type 0: knowledge; 1: interview
     * @return
     */
    @PostMapping("/users_like")
    public Object addLike(@RequestBody CollectLikeDTO dto, @RequestHeader("token") String token)
    {
        // try{
        //     if(dto.getType() == 0)
        //     {
        //         int resCode = collectionService.addLikeKnowledge(dto.getEmail(), dto.getKnowledge_id());
        //         if(resCode == 0)
        //         {
        //             return new Result<Object>("00", "Success.", token, null);
        //         }
        //         else
        //         {
        //             return new Result<Object>("09", "Already liked such question.", token, null);
        //         }
        //     }
        //     else
        //     {

        //     }
        // } catch(Exception exception)
        // {
        //     return new Result<Object>("99", "Internal service error.", token, null);
        // }
        String res = "";
        return res;
    }

    @GetMapping("/users_like")
    public Object getAllLike()
    {
        String result = "";
        return result;
    }

    @DeleteMapping("/users_like")
    public Object cancelLike()
    {
        String res = "";
        return res;
    }


}