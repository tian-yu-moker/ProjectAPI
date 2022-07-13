package com.hku.projectapi.Beans.Programming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerJudgement
{
    // 1: compile fail
    private int errorCode;
    private String message;
    private String ex;

    public AnswerJudgement(int errorCode, String message)
    {
        this.errorCode = errorCode;
        this.message = message;
    }
}
