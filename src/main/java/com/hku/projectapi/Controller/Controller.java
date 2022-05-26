package com.hku.projectapi.Controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"*","null"})
@RestController
public class Controller
{
    @RequestMapping("/test")
    public String test()
    {
        return "Tian Yu";
    }
}
