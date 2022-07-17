package com.hku.projectapi.Programming.Judgement;

import com.hku.projectapi.Beans.Programming.ProgrammingQuestionBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BaseJudgement
{
    public String filePath;
    public ProgrammingQuestionBean programInfo;
    public String uuid;

    public BaseJudgement(ProgrammingQuestionBean programInfo, String filePath, String uuid)
    {
        this.programInfo = programInfo;
        this.filePath = filePath;
        this.uuid = uuid;
    }
}
