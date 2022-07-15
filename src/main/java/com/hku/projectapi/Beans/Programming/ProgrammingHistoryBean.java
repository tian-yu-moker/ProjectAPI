package com.hku.projectapi.Beans.Programming;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.hku.projectapi.Beans.Interview.InterviewBean;
import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("programming_history")
public class ProgrammingHistoryBean
{
    @TableField("uuid")
    private String uuid;

    @TableField("userId")
    private String userId;

    @TableField("questionId")
    private Integer questionId;

    @TableField("upload_content")
    private String uploadContent;

    @TableField("upload_time")
    private Timestamp uploadTime;

    @TableField("stdout")
    private String stdout;

    @TableField("stderr")
    private String stderr;

    @TableField("status")
    private String status;

    @TableField(value = "fail_cases", typeHandler = JacksonTypeHandler.class)
    private List<GeneralBean> failedCases;
}
