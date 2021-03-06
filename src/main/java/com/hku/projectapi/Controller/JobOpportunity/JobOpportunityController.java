package com.hku.projectapi.Controller.JobOpportunity;

import com.hku.projectapi.Beans.JobOpportunity.JobOpportunityBean;
import com.hku.projectapi.Beans.PageRequestDTO;
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
    @PostMapping("load")
    public Result load(@RequestHeader String token, @RequestBody PageRequestDTO pageRequestDTO)
    {
        String updatedToken = "";
        try {
            updatedToken = JwtUtil.updateToken(token);
            System.out.println(updatedToken);
            Result result = jobOpportunityService.searchByPage(pageRequestDTO);
            result.setToken(updatedToken);
            return result;
        }catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
    }

    @GetMapping("query")
    public Result searchById(@RequestHeader String token, @RequestParam String uuid)
    {
        String updatedToken = "";
        try {
            updatedToken = JwtUtil.updateToken(token);
            Result res = jobOpportunityService.searchById(uuid);
            res.setToken(updatedToken);
            return res;
        }catch (Exception e){
            return new Result("97", "Invalid token, please login.", null);
        }
    }
}
