package com.hku.projectapi.Mapper.Knowledge;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface KnowledgeQuestionMapper extends BaseMapper<KnowledgeQuestionBean>
{
    List<KnowledgeQuestionBean> selectByTags(@Param("company") String company, @Param("tag") String tag);
}
