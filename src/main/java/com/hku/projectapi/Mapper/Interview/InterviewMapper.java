package com.hku.projectapi.Mapper.Interview;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.Interview.InterviewBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InterviewMapper extends BaseMapper<InterviewBean>
{

}
