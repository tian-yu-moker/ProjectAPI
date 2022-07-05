package com.hku.projectapi.Beans.Dictionary;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("company_dictionary")
public class CompanyBean
{
    @TableField("id")
    private Integer id;

    @TableField("label")
    private String label;
}
