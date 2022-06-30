package com.hku.projectapi.Beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("knowledge_answer")
public class KnowledgeAnswerBean
{
    @TableField("knowledge_answer_id")
    private String knowledgeAnswerId;

    @TableField("knowledge_id")
    private String knowledgeId;

    @TableField("answer_provider_id")
    private String answerProviderId;

    @TableField("content")
    private String content;

    @TableField("upload_time")
    private Timestamp uploadTime;

    @TableField("last_modified_time")
    private Timestamp lastModifiedTime;

}
