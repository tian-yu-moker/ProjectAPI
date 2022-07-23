package com.hku.projectapi.Beans.Collection;

import com.hku.projectapi.Beans.Interview.InterviewBean;
import com.hku.projectapi.Beans.Knowledge.KnowledgeQuestionBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollectionQueryDTO<T>
{
    private T interviews;
    private T knowledge;
}
