package com.hku.projectapi.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.KnowledgeQuestion;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface KnowledgeQuestionMapper extends BaseMapper<KnowledgeQuestion>
{
}
