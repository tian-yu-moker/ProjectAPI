package com.hku.projectapi.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.knowledge_like;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//@Repository
@Mapper
public interface KnowledgeCollectionMapper extends BaseMapper<knowledge_like>
{

}
