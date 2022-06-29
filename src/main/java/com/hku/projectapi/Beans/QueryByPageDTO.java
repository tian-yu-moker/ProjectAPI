package com.hku.projectapi.Beans;

import lombok.Data;

@Data
public class QueryByPageDTO<T>
{
    private QueryInfo queryInfo;
    private T entities;
}
