package com.hku.projectapi.Controller.JobOpportunity;

import com.hku.projectapi.Beans.JobOpportunity.JobOpportunityBean;
import com.hku.projectapi.Beans.QueryByPageDTO;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Service.JobOpportunity.JobOpportunityService;
import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*","null"})
@RestController
@RequestMapping("/job_service")
public class JobOpportunityController
{
    @Autowired
    private JobOpportunityService jobOpportunityService;

    @PostMapping("create")
    public Result create(@RequestHeader String token, @RequestBody JobOpportunityBean jobOpportunityBean)
    {
        String updatedToken = "";
        try {
            updatedToken = JwtUtil.updateToken(token);
            jobOpportunityService.create(jobOpportunityBean);
            return new Result("00", "Success.", updatedToken);
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
            return new Result("00", "Success.", updatedToken);
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
            return new Result("00", "Success.", updatedToken);
        }catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
    }
}
