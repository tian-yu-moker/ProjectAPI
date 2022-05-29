package com.hku.projectapi.Controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hku.projectapi.Beans.NormalResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"*", "null"})
@RestController

public class Graduate_timeController {

    private JdbcTemplate jdbcTemplate;

    public Graduate_timeController() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://120.77.98.16:3306/comp7705?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("0611");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping(value = "/login_register/graduate_time", method = RequestMethod.POST)
    public Object Time(@RequestBody String request) {
        JsonObject obj = new JsonParser().parse(request).getAsJsonObject();
        String email = obj.get("email").toString();
        String year = obj.get("year").toString();
        String month=obj.get("month").toString();
        NormalResponse response = new NormalResponse();
        String querySQL = "SELECT count(*) FROM users WHERE email=" + email;
        int number = jdbcTemplate.queryForObject(querySQL, Integer.class);
        System.out.println(number);
        if (number > 0) {
            String date=year+"-"+month+"-"+"01";
            String insertSQL = "UPDATE users SET Graduate_Date=\"" + date +"\""+ " where email=" + email;
            System.out.println(insertSQL);
            jdbcTemplate.execute(insertSQL);
            response.setCode("00");
            response.setDescription("Success.");
        } else {
            response.setCode("");
            response.setDescription("Account not exist");
        }
        return response;


    }
}
