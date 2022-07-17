package com.hku.projectapi.Beans.JobOpportunity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("job_opportunities")
public class JobOpportunityBean
{
    @TableField("uuid")
    private String uuid;

    @TableField("company")
    private String company;

    @TableField("job_position")
    private String jobPosition;

    @TableField("job_location")
    private String jobLocation;

    @TableField("job_description_short")
    private String descriptionShort;

    @TableField("job_description_full")
    private String descriptionFull;

    @TableField("salary")
    private String salary;

    @TableField("hr_contract")
    private String hrContract;

    @TableField("upload_time")
    private Timestamp uploadTime;

    @TableField("expiry_time")
    private Timestamp expiryTime;

    // need to disable the card (not allowed to be clicked)
    @TableField(exist = false)
    private boolean isExpired;
}
