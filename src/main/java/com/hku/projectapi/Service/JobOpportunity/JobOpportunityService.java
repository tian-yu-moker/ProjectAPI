package com.hku.projectapi.Service.JobOpportunity;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.JobOpportunity.JobOpportunityBean;
import com.hku.projectapi.Beans.PageRequestDTO;
import com.hku.projectapi.Beans.QueryByPageDTO;
import com.hku.projectapi.Beans.QueryInfo;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Mapper.JobOpportunity.JobOpportunityMapper;
import com.hku.projectapi.Tools.UUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class JobOpportunityService extends ServiceImpl<JobOpportunityMapper, JobOpportunityBean>
{
    @Autowired
    private JobOpportunityMapper jobOpportunityMapper;

    public void create(JobOpportunityBean jobOpportunityDTO)
    {
        String uuid = UUidGenerator.getUUID32();
        jobOpportunityDTO.setUuid(uuid);
        jobOpportunityMapper.insert(jobOpportunityDTO);
    }

    public Result searchByPage(PageRequestDTO pageRequestDTO)
    {
        int curPage= pageRequestDTO.getPageFirst();
        int pageSize = pageRequestDTO.getPageSizeFirst();
        QueryWrapper<JobOpportunityBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.select().orderByDesc("upload_time");
        Page<JobOpportunityBean> resPage = jobOpportunityMapper.selectPage(new Page<>(curPage, pageSize), queryWrapper);
        List<JobOpportunityBean> records = resPage.getRecords();

        for(JobOpportunityBean beans:records){
            Timestamp expiry = beans.getExpiryTime();
            Timestamp curTime = new Timestamp(new Date().getTime());
            if(expiry.before(curTime)){
                beans.setExpired(true);
            }else{
                beans.setExpired(false);
            }
        }

        QueryByPageDTO result = new QueryByPageDTO();
        QueryInfo queryInfo = new QueryInfo();
        queryInfo.setPageSize(pageRequestDTO.getPageSizeFirst());
        queryInfo.setCurrentPage(pageRequestDTO.getPageFirst());
        queryInfo.setTotalRecord(resPage.getTotal());
        result.setQueryInfo(queryInfo);
        result.setEntities(records);
        return new Result("00", "Success.", null, result);
    }

    public void searchById()
    {

    }
}
