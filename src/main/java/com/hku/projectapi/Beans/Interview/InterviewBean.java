package com.hku.projectapi.Beans.Interview;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hku.projectapi.Beans.PageRequestDTO;
import com.hku.projectapi.Beans.QueryByPageDTO;
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

    @TableField(exist = false)
    private String providerName;

    @TableField("title")
    private String title;

    @TableField("description")
    private String description;

    @TableField("company")
    private String company;

    @TableField("upload_time")
    private Timestamp uploadTime;

    @TableField("level")
    private String level;

    @TableField("interview_time")
    private Timestamp interviewTime;

    @TableField("job_position")
    private String position;

    @TableField("job_location")
    private String location;

    @TableField(exist = false)
    private int isLiked;

    @TableField(exist = false)
    private QueryByPageDTO questions;
}
