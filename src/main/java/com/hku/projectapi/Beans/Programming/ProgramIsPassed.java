package com.hku.projectapi.Beans.Programming;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("programming_pass")
public class ProgramIsPassed
{
    @TableField("userId")
    private String userId;
    @TableField("questionId")
    private int questionId;
    @TableField("isPassed")
    private int isPassed;
}
