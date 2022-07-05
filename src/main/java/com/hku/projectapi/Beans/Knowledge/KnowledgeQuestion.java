package com.hku.projectapi.Beans.Knowledge;

import lombok.Data;

@Data
public class KnowledgeQuestion
{
    private String question_content;
    private String answer_list;
    private String userid;
    private String interview_id;
    private String comment_list;
    private String company;
    private String tag;
}
