package com.hku.projectapi.Beans.Programming;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("programming_questions")
public class ProgrammingQuestionBean
{
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    @TableField("level")
    private String level;

    // Base, single, array, list(collection)
    @TableField("test_case_type")
    private Integer testCaseType;

    @TableField("test_cases")
    private String testCases;

    @TableField("prepare_code")
    private String prepareCode;

    @TableField("post_code")
    private String postCode;
}
