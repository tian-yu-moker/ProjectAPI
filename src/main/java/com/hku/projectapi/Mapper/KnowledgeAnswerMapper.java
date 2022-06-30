package com.hku.projectapi.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.KnowledgeAnswerBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface KnowledgeAnswerMapper extends BaseMapper<KnowledgeAnswerBean>
{

}
