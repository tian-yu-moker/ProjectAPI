package com.hku.projectapi.Beans;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class email_verification_code
{
    private String email;
    private String code;
    private Timestamp generate;
    private Timestamp expiry;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Timestamp getGenerate() {
        return generate;
    }

    public void setGenerate(Timestamp generate) {
        this.generate = generate;
    }

    public String getCode()
    {
        return code;
    }

    public void setCode(String code)
    {
        this.code = code;
    }

    public Timestamp getExpiry() {
        return expiry;
    }

    public void setExpiry(Timestamp expiry) {
        this.expiry = expiry;
    }
}
