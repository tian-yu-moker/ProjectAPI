package com.hku.projectapi.Service.User;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Beans.User.UserBean;
import com.hku.projectapi.Mapper.Users.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends ServiceImpl<UserMapper, UserBean>
{
    @Autowired
    private UserMapper userMapper;

    public Result searchAlumni(String userId)
    {
        QueryWrapper<UserBean> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", userId);
        UserBean userBean = userMapper.selectOne(queryWrapper);
        String company = userBean.getCompany();
        String school = userBean.getSchool();
        // Select list
        List<UserBean> alumni = userMapper.searchAlumni(school, company);
        if(alumni.size() == 0){
            return new Result("15", "Sorry, not alumni found.", null);
        }
        for(UserBean bean:alumni){
            bean.setPassword("");
        }

        return new Result("00", "Success.", null, alumni);
    }


}
