package com.hku.projectapi.Controller.UserProfile;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hku.projectapi.Beans.NormalResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = {"*","null"})
@RestController

public class ProfileSettingController {

    private JdbcTemplate jdbcTemplate;

    public ProfileSettingController()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://120.77.98.16:3306/comp7705?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("0611");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping(value="/login_register/profile_setting",method= RequestMethod.POST)
    public Object School(@RequestBody String request) {
        JsonObject obj = new JsonParser().parse(request).getAsJsonObject();
        String email=obj.get("email").toString();
        String company = obj.get("company").toString();
        String username = obj.get("username").toString();
        String identity = obj.get("identity").toString();
        String school = obj.get("school").toString();
        String year = obj.get("year").toString();
        String month=obj.get("month").toString();
        String YoE = obj.get("YoE").toString();
        NormalResponse response = new NormalResponse();
        String querySQL="SELECT count(*) FROM users WHERE email="+email;
        int number=jdbcTemplate.queryForObject(querySQL, Integer.class);
        System.out.println(number);
        if(number>0) {
            String date=year+"-"+month+"-"+"01";
            String insertSQL="UPDATE users SET company=" + company + ",Name="+username+",Type="+identity+",school="
                    +school+",Graduate_Date=\"" + date +"\""+",YoE="+YoE+""+" where email="+email;
            System.out.println(insertSQL);
            jdbcTemplate.execute(insertSQL);
            response.setCode("00");
            response.setDescription("Success.");
        }else{
            response.setCode("02");
            response.setDescription("Account not exist, please register one first");
        }
        return response;
    }

}
