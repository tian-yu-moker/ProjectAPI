package com.hku.projectapi.Mapper.Programming;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ProgrammingQuestionMapper extends BaseMapper<ProgrammingQuestionBean>
{

}
