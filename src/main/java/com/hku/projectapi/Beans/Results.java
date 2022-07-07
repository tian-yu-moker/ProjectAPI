package com.hku.projectapi.Beans;

import com.hku.projectapi.Tools.ResultCodeMsg;
import lombok.Data;

@Data
public class Results<T>
{
    private ResultCodeMsg msgCode;
    private String token;
    private T data;

    public Results()
    {
        super();
    }

    public Results(ResultCodeMsg codeMsg, String token, T data)
    {
        this.msgCode = codeMsg;
        this.token = token;
        this.data = data;
    }

    public Results(ResultCodeMsg codeMsg, String token)
    {
        this.msgCode = codeMsg;
        this.token = token;
    }
}
