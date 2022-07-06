package com.hku.projectapi.Beans.Interview;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("interview")
public class InterviewBean
{
    @TableField("interview_id")
    private String interviewId;

    @TableField("providerId")
    private String providerId;

    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    @TableField("company")
    private String company;

    @TableField("upload_time")
    private Timestamp uploadTime;
}
