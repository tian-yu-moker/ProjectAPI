package com.hku.projectapi.Programming.TestCaseBeans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class is the general parameters list of questions
 * @param <T>
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneralBean<T>
{
    private T param1;
    private T param2;
    private T param3;
    private T param4;
    private T param5;
}
