package com.hku.projectapi.Beans.Programming;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "programming_questions", autoResultMap = true)
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

    @TableField(value = "test_cases", typeHandler = JacksonTypeHandler.class)
    private List<GeneralBean> testCases;

    @TableField("prepare_code")
    private String prepareCode;

    @TableField("default_method_name")
    private String defaultMethodName;

    @TableField("return_type")
    private Integer returnType;

    @TableField("param_length")
    private Integer paramLen;

    @TableField("param_types")
    private String paramTypes;

    @TableField("test_num")
    private Integer testNum;

    @TableField(exist = false)
    private int isPassed;
}
