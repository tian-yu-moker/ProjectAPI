package com.hku.projectapi.Controller.MyPost;

import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Service.MyPost.MyPostService;
import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*","null"})
@RestController
public class MyPostController
{
    @Autowired
    private MyPostService myPostService;

    @GetMapping("my_posts")
    public Result getMyPosts(@RequestHeader String token)
    {
        try{
            Result res = myPostService.load(token);
            res.setToken(JwtUtil.updateToken(token));
            return res;
        } catch (Exception e){
            return new Result("99", "Internal server error.", null);
        }
    }
}
