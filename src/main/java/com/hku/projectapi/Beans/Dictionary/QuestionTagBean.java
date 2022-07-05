package com.hku.projectapi.Beans.Dictionary;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("question_tags")
public class QuestionTagBean
{
    @TableField("id")
    private Integer id;

    @TableField("label")
    private String label;
}
