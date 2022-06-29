package com.hku.projectapi.Beans;

import lombok.Data;

@Data
public class Result<T>
{
    private String code;
    private String description;
    private String token;
    private T data;

    public Result()
    {
        super();
    }

    public Result(String code, String description, String token, T data)
    {
        this.code = code;
        this.description = description;
        this.token = token;
        this.data = data;
    }

    public Result(String code, String description, String token)
    {
        this.code = code;
        this.description = description;
        this.token = token;
    }

    public Result(String code)
    {
        this.code = code;
    }
}
