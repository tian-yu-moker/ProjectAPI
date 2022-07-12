package com.hku.projectapi.Beans.MyPost;

import com.hku.projectapi.Beans.QueryByPageDTO;
import lombok.Data;

@Data
public class MyPostDTO
{
    private QueryByPageDTO interviews;
    private QueryByPageDTO questions;
}
