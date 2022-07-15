package com.hku.projectapi.Mapper.Programming;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.Programming.ProgrammingHistoryBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProgrammingHistoryMapper extends BaseMapper<ProgrammingHistoryBean>
{

}
