package com.hku.projectapi.Controller.JobOpportunity;

import com.hku.projectapi.Beans.QueryByPageDTO;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*","null"})
@RestController
@RequestMapping("/job_service")
public class JobOpportunityController
{
    @PostMapping("create")
    public Result create(@RequestHeader String token)
    {
        String updatedToken = "";
        try {
            updatedToken = JwtUtil.updateToken(token);
        }catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
    }

    // Load by page
    @GetMapping("load")
    public Result load(@RequestBody String token, @RequestBody QueryByPageDTO queryByPageDTO)
    {
        String updatedToken = "";
        try {
            updatedToken = JwtUtil.updateToken(token);
        }catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
    }

    @GetMapping("query_by_id")
    public Result searchById(@RequestHeader String token, @RequestParam String uuid)
    {
        String updatedToken = "";
        try {
            updatedToken = JwtUtil.updateToken(token);
        }catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
    }
}
