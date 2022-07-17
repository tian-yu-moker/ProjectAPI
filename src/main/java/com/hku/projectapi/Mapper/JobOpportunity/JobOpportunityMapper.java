package com.hku.projectapi.Mapper.JobOpportunity;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.JobOpportunity.JobOpportunityBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface JobOpportunityMapper extends BaseMapper<JobOpportunityBean>
{

}
