package com.hku.projectapi.Mapper;

import com.hku.projectapi.Beans.Collection.KnowledgeLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface KnowledgeLikeMapper extends BaseMapper<KnowledgeLike>
{
    
}