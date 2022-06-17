package com.hku.projectapi.Controller;

import static org.mockito.Mockito.lenient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.hku.projectapi.Beans.Result;
import com.hku.projectapi.Service.CollectionLikeService;

@CrossOrigin(origins = {"*","null"})
@RestController
public class CollectionController 
{
    @Autowired
    private CollectionLikeService collectionService;


    @PostMapping("/users_like")
    public Object addLike()
    {
        String res = "";
        return res;
    }

    @GetMapping("/users_like")
    public Object getAllLike()
    {
        String result = "";
        return result;
    }

    @DeleteMapping("/users_like")
    public Object cancelLike()
    {
        String res = "";
        return res;
    }


}
