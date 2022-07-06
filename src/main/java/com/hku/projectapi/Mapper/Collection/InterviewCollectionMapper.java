package com.hku.projectapi.Mapper.Collection;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.Collection.InterviewLike;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface InterviewCollectionMapper extends BaseMapper<InterviewLike>
{

}
