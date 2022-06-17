package com.hku.projectapi.Controller;

import org.springframework.web.bind.annotation.*;

import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Service.CollectionLikeService;

@CrossOrigin(origins = {"*","null"})
@RestController
public class CollectionController 
{
    @Autowired
    private CollectionLikeService collections;

    @GetMapping("getUsersLike")
    public Object getAllLike()
    {

        return "";
    }
}
