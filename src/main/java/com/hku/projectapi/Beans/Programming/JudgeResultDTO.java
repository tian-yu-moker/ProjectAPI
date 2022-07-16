package com.hku.projectapi.Beans.Programming;

import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JudgeResultDTO
{
    private String questionId;
    // Accept, Reject, Error: 0, 1, 2
    private int status;
    // Standard output by user
    private String stdout;
    // The error message, if any
    private String errorMsg;
    // The error test cases, if any
    private GeneralBean errorTestCase;
}
