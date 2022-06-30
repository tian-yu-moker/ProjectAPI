package com.hku.projectapi.Beans;

import lombok.Data;

@Data
public class KnowledgeAnswerRequestDTO
{
    private String knowledgeId;
    private String answerProvider;
    private String content;
}
