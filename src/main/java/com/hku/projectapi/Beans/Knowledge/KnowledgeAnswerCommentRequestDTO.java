package com.hku.projectapi.Beans.Knowledge;

import lombok.Data;

@Data
public class KnowledgeAnswerCommentRequestDTO
{
    private String knowledgeId;
    private String provider;
    private String content;
}
