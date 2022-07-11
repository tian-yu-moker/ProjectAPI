package com.hku.projectapi.Beans.Interview;

import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewQueryDTO
{
    private InterviewBean interview;
    private List<KnowledgeQuestionBean> questions;
}
