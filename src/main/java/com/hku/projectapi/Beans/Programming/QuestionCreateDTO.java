package com.hku.projectapi.Beans.Programming;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class QuestionCreateDTO
{
    private String title;

    private String description;

    private String level;

    private Integer testCaseType;

    private String testCases;

    private String prepareCode;

    private String postCode;
}
