package com.hku.projectapi.Tools;

public enum ResultCodeMsg
{
    SUCCESS("00", "Success."),
    INTERNAL_ERROR("99", "Internal server error.");

    ResultCodeMsg(String code, String description) {
        this.code = code;
        this.description = description;
    }

    private String code;
    private String description;
}
