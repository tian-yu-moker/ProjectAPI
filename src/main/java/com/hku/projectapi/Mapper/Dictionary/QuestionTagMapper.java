package com.hku.projectapi.Mapper.Dictionary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.Dictionary.QuestionTagBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface QuestionTagMapper extends BaseMapper<QuestionTagBean>
{

}
