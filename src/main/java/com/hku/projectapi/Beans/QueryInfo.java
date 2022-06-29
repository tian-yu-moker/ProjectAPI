package com.hku.projectapi.Beans;

import lombok.Data;

@Data
public class QueryInfo
{
    private int currentPage;
    private int pageSize;
    private long totalRecord;
}
