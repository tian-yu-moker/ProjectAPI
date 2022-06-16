package com.hku.projectapi.Beans;

public class NormalResponse
{
    private String code;
    private String description;
    private String token;

    public NormalResponse()
    {

    }

    public NormalResponse(String code, String description)
    {
        this.code = code;
        this.description = description;
    }

    public NormalResponse(String code, String description, String token)
    {
        this.code = code;
        this.description = description;
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
