package com.hku.projectapi.Service.JobOpportunity;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.JobOpportunity.JobOpportunityBean;
import com.hku.projectapi.Mapper.JobOpportunity.JobOpportunityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobOpportunityService extends ServiceImpl<JobOpportunityMapper, JobOpportunityBean>
{
    @Autowired
    private JobOpportunityMapper jobOpportunityMapper;

    public void create()
    {

    }

    public void searchByPage()
    {

    }

    public void searchById()
    {

    }
}
