package com.hku.projectapi.Beans;

import lombok.Data;

@Data
public class KnowledgeAnswerCommentRequestDTO
{
    private String knowledgeId;
    private String provider;
    private String content;
}
