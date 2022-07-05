package com.hku.projectapi.Mapper.Dictionary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.Dictionary.CompanyBean;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CompanyMapper extends BaseMapper<CompanyBean>
{

}
