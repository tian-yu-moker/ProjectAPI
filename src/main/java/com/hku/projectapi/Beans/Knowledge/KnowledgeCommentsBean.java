package com.hku.projectapi.Beans.Knowledge;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("knowledge_comment")
public class KnowledgeCommentsBean
{
    @TableField("knowledge_comments_id")
    private String knowledgeCommentId;

    @TableField("knowledge_id")
    private String knowledgeId;

    @TableField("comment_provider_id")
    private String providerId;

    @TableField("content")
    private String content;

    @TableField("upload_time")
    private Timestamp uploadTime;

    @TableField("last_modified_time")
    private Timestamp lastModifiedTime;
}
