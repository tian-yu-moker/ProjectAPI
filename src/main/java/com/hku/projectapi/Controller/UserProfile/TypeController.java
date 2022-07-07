package com.hku.projectapi.Controller.UserProfile;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hku.projectapi.Beans.NormalResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = {"*", "null"})
@RestController

public class TypeController {

    private JdbcTemplate jdbcTemplate;

    public TypeController() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://120.77.98.16:3306/comp7705?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("0611");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping(value = "/login_register/type", method = RequestMethod.POST)
    public Object Type(@RequestBody String request) {

        JsonObject obj = new JsonParser().parse(request).getAsJsonObject();
        String email = obj.get("email").toString();
        String type = obj.get("type").toString();
        NormalResponse response = new NormalResponse();
        String querySQL = "SELECT count(*) FROM users WHERE email=" + email;
        int number = jdbcTemplate.queryForObject(querySQL, Integer.class);
        System.out.println(number);
        if (number > 0) {
            String insertSQL = "UPDATE users SET Type=" + type + " " + " where email=" + email;
            System.out.println(insertSQL);
            jdbcTemplate.execute(insertSQL);
            response.setCode("00");
            response.setDescription("Success.");
        } else {
            response.setCode("02");
            response.setDescription("Account not exist, please register one first");
        }
        return response;
    }


}
