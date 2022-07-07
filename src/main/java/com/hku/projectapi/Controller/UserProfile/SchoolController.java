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

public class SchoolController {

    private JdbcTemplate jdbcTemplate;

    public SchoolController() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://120.77.98.16:3306/comp7705?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("0611");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping(value = "/login_register/school_detail", method = RequestMethod.POST)
    public Object School_Detail(@RequestBody String request) {
        JsonObject obj = new JsonParser().parse(request).getAsJsonObject();
        String email = obj.get("email").toString();
        String School = obj.get("school").toString();
        String Education_bg = obj.get("background").toString();
        //这里可以在页面上做一个选择框，让大家选择自己是本科生，研究生还是博士生
        String graduate = obj.get("graduate").toString();
        //这里是1为毕业生，0为没有毕业的学生
        NormalResponse response = new NormalResponse();
        if (School != null && Education_bg != null && (graduate.equals("1") || graduate.equals("0"))) {
            String insertSQL = "INSERT INTO schools(email,school,education_bg,graduate) VALUES (\"" + email + "\",\"" + School + "\",\"" +
                    Education_bg + "\",\"" + graduate + ")";
            System.out.println(insertSQL);
            jdbcTemplate.execute(insertSQL);
            response.setCode("00");
            response.setDescription("Success.");
        } else {
            response.setCode("03");
            response.setDescription("Account already exists.");
        }
        return response;
    }

    @RequestMapping(value = "/login_register/school", method = RequestMethod.POST)
    public Object School(@RequestBody String request) {
        JsonObject obj = new JsonParser().parse(request).getAsJsonObject();
        String email = obj.get("email").toString();
        System.out.println(email);
        String School = obj.get("school").toString();
        NormalResponse response = new NormalResponse();
        String querySQL = "SELECT count(*) FROM users WHERE email=" + email;
        int number = jdbcTemplate.queryForObject(querySQL, Integer.class);
        System.out.println(number);
        if (number > 0) {
            String insertSQL = "UPDATE users SET school=" + School + " " + " where email=" + email;
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
