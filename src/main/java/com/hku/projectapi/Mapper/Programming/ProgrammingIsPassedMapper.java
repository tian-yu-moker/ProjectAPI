package com.hku.projectapi.Mapper.Programming;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.Programming.ProgramIsPassed;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProgrammingIsPassedMapper extends BaseMapper<ProgramIsPassed>
{

}
