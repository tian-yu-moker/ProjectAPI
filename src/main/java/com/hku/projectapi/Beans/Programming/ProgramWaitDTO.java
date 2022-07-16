package com.hku.projectapi.Beans.Programming;

import lombok.Data;

@Data
public class ProgramWaitDTO
{
    private String uuid;
    private Long waitMinutesToRequest;
}
