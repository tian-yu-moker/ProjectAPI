package com.hku.projectapi.Controller;

import org.springframework.web.bind.annotation.*;

import com.hku.projectapi.Beans.Result;

@CrossOrigin(origins = {"*","null"})
@RestController
public class CollectionController 
{
    @GetMapping("getUsersLike")
    public Object getAllLike()
    {
        return "";
    }
}
