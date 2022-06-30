package com.hku.projectapi.Mapper.Knowledge;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.Knowledge.KnowledgeCommentsBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
@Mapper
public interface KnowledgeCommentMapper extends BaseMapper<KnowledgeCommentsBean>
{

}
