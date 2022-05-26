package com.hku.projectapi.Controller;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = {"*","null"})
@RestController
public class Controller
{
    private JdbcTemplate jdbcTemplate;

    public Controller() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://120.77.98.16:3306/comp7705?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("0611");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @RequestMapping("/test")
    public String test()
    {
        return "Tian Yu";
    }

    @RequestMapping("/testDB")
    public String testDB()
    {
        String sql = "Select * from test";
        List<Map<String, Object>> info = jdbcTemplate.queryForList(sql);
        System.out.println(info);
        return "Tian Yu";
    }
}
