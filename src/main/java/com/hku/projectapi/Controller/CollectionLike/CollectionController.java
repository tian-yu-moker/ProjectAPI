package com.hku.projectapi.Controller.CollectionLike;


import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import com.hku.projectapi.Beans.Collection.CollectLikeDTO;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Service.Collection.CollectionLikeService;

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
    public Result addLike(@RequestBody CollectLikeDTO dto, @RequestHeader("token") String token)
    {
        String email = "";
        try{
            email = JwtUtil.getUserId(token);
        } catch (Exception e){
            return new Result("97", "Token invalid.", null);
        }
         try{
             if(dto.getType() == 0)
             {
                 int resCode = collectionService.addLikeKnowledge(email, dto.getId());
                 if(resCode == 0)
                 {
                     try{
                         token = JwtUtil.updateToken(token);
                         return new Result("00", "Success.", token, null);
                     } catch (Exception e){
                         return new Result("97", "Token invalid.", null);
                     }
                 }
                 else if(resCode == -1)
                 {
                     try{
                         token = JwtUtil.updateToken(token);
                         return new Result("11", "Cancel the collection successfully.", token, null);
                     } catch (Exception e){
                         return new Result("97", "Token invalid.", null);
                     }
                 }
                 else
                 {
                     try{
                         token = JwtUtil.updateToken(token);
                         return new Result("12", "No such record.", token, null);
                     } catch (Exception e){
                         return new Result("97", "Token invalid, please login", null);
                     }
                 }
             }
             // For interview
             else
             {
                 int resCode = collectionService.addInterviewLike(email, dto.getId());
                 if(resCode == 0){
                     try{
                         token = JwtUtil.updateToken(token);
                         return new Result("00", "Success.", token, null);
                     } catch (Exception e){
                         return new Result("97", "Token invalid.", null);
                     }
                 }
                 else if(resCode == -1)
                 {
                     try{
                         token = JwtUtil.updateToken(token);
                         return new Result("11", "Cancel the collection successfully.", token, null);
                     } catch (Exception e){
                         return new Result("97", "Token invalid.", null);
                     }
                 }
                 else
                 {
                     try{
                         token = JwtUtil.updateToken(token);
                         return new Result("12", "No such record.", token, null);
                     } catch (Exception e){
                         return new Result("97", "Token invalid, please login", null);
                     }
                 }
             }
         } catch(Exception exception)
         {
             return new Result("99", "Internal service error.", token, null);
         }
    }

    /**
     * Get all collections.
     * @return
     */
    @GetMapping("/users_like")
    public Result loadAllCollection(@RequestHeader("token") String token)
    {
        Result res = collectionService.getWholeCollection(token);
        if(res.getCode() == "00" || res.getCode() == "99"){
            try{
                token = JwtUtil.updateToken(token);
                res.setToken(token);
                return res;
            } catch (Exception e){
                return new Result("97", "Token invalid, please login", null);
            }
        }else{
            return res;
        }
    }
}
