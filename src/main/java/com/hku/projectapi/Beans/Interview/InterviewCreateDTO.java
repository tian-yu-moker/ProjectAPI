package com.hku.projectapi.Beans.Interview;

import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewCreateDTO
{
    private String title;
    private String description;
    private String company;
    private String location;
    private String level;
    private String position;
    private Date interview_time;
    private List<KnowledgeQuestionDTO> questions;
}
