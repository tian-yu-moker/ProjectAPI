package com.hku.projectapi.Mapper.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.Collection.KnowledgeLike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

//@Repository
@Mapper
@Repository
public interface KnowledgeCollectionMapper extends BaseMapper<KnowledgeLike>
{

}
