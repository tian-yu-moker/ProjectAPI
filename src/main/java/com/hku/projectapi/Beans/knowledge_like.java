package com.hku.projectapi.Beans;
import java.sql.Timestamp;

import lombok.Data;

@Data
public class knowledge_like
{
    private String email;
    private String knowledge_id;
    private Timestamp add_date;

    public knowledge_like(String email, String knowledge_id, Timestamp add_date)
    {
        this.email = email;
        this.knowledge_id = knowledge_id;
        this.add_date = add_date;
    }
}