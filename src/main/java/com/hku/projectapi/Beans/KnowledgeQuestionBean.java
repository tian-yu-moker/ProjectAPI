package com.hku.projectapi.Beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("knowledge_questions")
public class KnowledgeQuestionBean
{
    @TableField("knowledge_id")
    private String knowledgeId;
    @TableField("question_content")
    private String question_content;
    @TableField("answer_list")
    private String answer_list;
    @TableField("userid")
    private String userid;
    @TableField("comment_list")
    private String comment_list;
    @TableField("company")
    private String company;
    @TableField("tag")
    private String tag;

    @TableField("upload_time")
    private Timestamp uploadTime;
}
