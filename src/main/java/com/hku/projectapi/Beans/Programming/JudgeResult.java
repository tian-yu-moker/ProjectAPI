package com.hku.projectapi.Beans.Programming;

import com.hku.projectapi.Programming.TestCaseBeans.GeneralBean;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JudgeResult<T>
{
    private String status;
    private GeneralBean failedCase;
    private String msg;
}
