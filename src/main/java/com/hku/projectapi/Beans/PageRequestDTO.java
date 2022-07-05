package com.hku.projectapi.Beans;

import lombok.Data;

@Data
public class PageRequestDTO
{
    // First level, e.g. knowledge questions etc.
    private int pageFirst;
    private int pageSizeFirst;
    // Children, e.g. answers, comments
    private int pageSecond;
    private int pageSizeSecond;
    // Children, e.g. answers, comments
    private int pageThird;
    private int pageSizeThird;
    private int type;
    // Two tags
    private int tag1;
    private int tag2;
}
