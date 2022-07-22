package com.hku.projectapi.Controller.UserProfile;

import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Service.User.UserService;
import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*","null"})
@RestController
public class UserAlumniController
{
    @Autowired
    private UserService userService;

    @GetMapping("get_alumni")
    public Result getAlumni(@RequestHeader String token)
    {
        String userId = "";
        String updatedToken = "";
        try {
            userId = JwtUtil.getUserId(token);
            updatedToken = JwtUtil.updateToken(token);
        }catch (Exception e){
            return new Result("98", "Invalid token, please login", null);
        }
        try {
            Result res = userService.searchAlumni(userId);
            res.setToken(updatedToken);
            return res;
        } catch (Exception e){
            return new Result("99", "Internal server error, with error msg <" + e.getMessage() + ">", updatedToken);
        }
    }
}
