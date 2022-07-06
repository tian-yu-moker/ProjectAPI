package com.hku.projectapi.Beans.Collection;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("interview_like")
public class InterviewLike
{
    @TableField("email")
    private String email;

    @TableField("interview_id")
    private String interviewId;

    @TableField("add_date")
    private Timestamp add_date;
}
