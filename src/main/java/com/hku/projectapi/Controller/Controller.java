package com.hku.projectapi.Controller;


import com.hku.projectapi.Tools.JwtUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


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

    @GetMapping("/author/hello")
    public String hello() {
        return "hello world";
    }

    @GetMapping("/auth/login")
    public String login(String username, String pass) {
        //假设数据库中查询到了该用户，这里测试先所及生成一个UUID，作为用户的id
        String userId = UUID.randomUUID().toString();

        //准备存放在IWT中的自定义数据
        Map<String, Object> info = new HashMap<>();
        info.put("username", "tom");
        info.put("pass", "admin");

        //生成JWT字符串
        String token = JwtUtil.sign(userId, info);

        return token;
    }
}
