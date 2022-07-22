package com.hku.projectapi.Mapper.Users;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hku.projectapi.Beans.User.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper extends BaseMapper<UserBean>
{
    List<UserBean> searchAlumni(@Param("school") String school, @Param("company") String company);
}
