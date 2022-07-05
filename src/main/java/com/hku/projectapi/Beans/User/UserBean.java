package com.hku.projectapi.Beans.User;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("users")
public class UserBean
{
    @TableField("Email")
    private String email;

    @TableField("Name")
    private String name;

    @TableField("Password")
    private String password;

    @TableField("is_admin")
    private Integer isAdmin;

    @TableField("School")
    private String school;

    @TableField("Graduate_Date")
    private Date graduateDate;

    @TableField("Type")
    private String type;

    @TableField("Company")
    private String company;

    @TableField("YoE")
    private String yoe;
}
