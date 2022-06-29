package com.hku.projectapi.Beans;

import lombok.Data;

@Data
public class PageRequestDTO
{
    private int page;
    private int pageSize;
    private int type;
}
