package com.hku.projectapi.Beans.Programming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompileResult
{
    private boolean isSuccess;
    private String errorMsg;
}
