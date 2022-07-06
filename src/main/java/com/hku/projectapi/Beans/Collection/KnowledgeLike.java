package com.hku.projectapi.Beans.Collection;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("knowledge_like")
public class KnowledgeLike
{
    @TableField("email")
    private String email;
    @TableField("knowledge_id")
    private String knowledge_id;
    @TableField("add_date")
    private Timestamp add_date;
}