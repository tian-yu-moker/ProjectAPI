package com.hku.projectapi.Beans.JobOpportunity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("job_opportunities")
public class JobOpportunityBean
{
    @TableField("uuid")
    private String uuid;
}
