package com.hku.projectapi.Beans.Interview;

import com.hku.projectapi.Beans.Knowledge.KnowledgeAnswerCommentRequestDTO;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewCreateDTO
{
    private String title;
    private String description;
    private String company;
    private List<KnowledgeQuestion> questions;
}
