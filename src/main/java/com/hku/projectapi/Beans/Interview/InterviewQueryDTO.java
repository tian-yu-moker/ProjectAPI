package com.hku.projectapi.Beans.Interview;

import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InterviewQueryDTO<T>
{
    private InterviewBean interview;
    private T questions;
}
