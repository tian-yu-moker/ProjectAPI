package com.hku.projectapi.Controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hku.projectapi.Beans.NormalResponse;
import com.hku.projectapi.Beans.Users;
import com.hku.projectapi.Service.RegisterService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;

import java.util.*;

@CrossOrigin(origins = {"*","null"})
@RestController

public class LoginRegisterController
{

    private JdbcTemplate jdbcTemplate;

    public LoginRegisterController()
    {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://120.77.98.16:3306/comp7705?useSSL=false&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&serverTimezone=Asia/Shanghai");
        dataSource.setUsername("root");
        dataSource.setPassword("0611");
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @RequestMapping(value = "/login_register/login", method = RequestMethod.POST)
    public Object doLogin(@RequestBody String request)
    {
        JsonObject obj = new JsonParser().parse(request).getAsJsonObject();
        String email = obj.get("email").toString();
        email = email.substring(1, email.length() - 1);
        String password = obj.get("password").toString();
        password = password.substring(1, password.length() - 1);
        String sql = "SELECT * FROM users WHERE email=\"" + email + "\"";
        List<Map<String, Object>> userInfo = jdbcTemplate.queryForList(sql);
        if(userInfo.size() == 0)
        {
            NormalResponse response = new NormalResponse();
            response.setCode("02");
            response.setDescription("No such account, please register first.");
            return response;
        }
        sql = "SELECT password FROM users WHERE email=\"" + email + "\"";
        String queriedPassword = jdbcTemplate.queryForObject(sql, String.class);
        System.out.println(queriedPassword);
        if(!password.equals(queriedPassword))
        {
            NormalResponse response = new NormalResponse();
            response.setCode("01");
            response.setDescription("Password incorrect.");
            return response;
        }
        else
        {
            Users user = new Users();
            for(Map<String,Object> map:userInfo)
            {
                Set<Map.Entry<String, Object>> entries = map.entrySet();
                Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
                while (iterator.hasNext())
                {
                    Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
                    if(entry.getKey().equals("Email"))
                    {
                        user.setEmail(entry.getValue().toString());
                    }
                    else if(entry.getKey().equals("Name"))
                    {
                        user.setName(entry.getValue().toString());
                    }
                    else if(entry.getKey().equals("is_admin"))
                    {
                        user.setIsAdmin(Integer.parseInt(entry.getValue().toString()));
                    }
                    else if(entry.getKey().equals("School"))
                    {
                        if(entry.getValue() != null)
                        {
                            user.setSchool(entry.getValue().toString());
                        }
                    }
                    else if(entry.getKey().equals("Type"))
                    {
                        if(entry.getValue() != null)
                        {
                            user.setType(entry.getValue().toString());
                        }
                    }
                    else if(entry.getKey().equals("Company"))
                    {
                        if(entry.getValue() != null)
                        {
                            user.setCompany(entry.getValue().toString());
                        }
                    }
                    else if(entry.getKey().equals("Graduate_Date"))
                    {
                        if(entry.getValue() != null)
                        {
                            user.setDate(entry.getValue().toString());
                        }
                    }
                }
            }
            return user;
        }
    }

    /**
     Register a new account.
     @Code
     00: successful
     02: account already exists
     */
    @RequestMapping(value = "/login_register/register", method = RequestMethod.POST)
    public Object doRegister(@RequestBody String request)
    {
        JsonObject obj = new JsonParser().parse(request).getAsJsonObject();
        String email = obj.get("email").toString();
        email = email.substring(1, email.length() - 1);
        String password = obj.get("password").toString();
        password = password.substring(1, password.length() - 1);
        String user_name = obj.get("user_name").toString();
        user_name = user_name.substring(1, user_name.length() - 1);
        // First check whether the account exists in the database.
        String checkSQL = "SELECT * FROM users WHERE email=\""+ email +"\"";
        List<Map<String, Object>> checkInfo = jdbcTemplate.queryForList(checkSQL);
        NormalResponse response = new NormalResponse();
        if(checkInfo.size() > 0)
        {
            response.setCode("03");
            response.setDescription("Account already exists.");
        }
        else
        {
            String insertSQL = "INSERT INTO users(email, password, name, is_admin) VALUES (\""+ email + "\",\"" + password + "\",\"" +
                    user_name + "\"," + 0 + ")";
            System.out.println(insertSQL);
            jdbcTemplate.execute(insertSQL);
            response.setCode("00");
            response.setDescription("Success.");
        }
        return response;
    }

    @RequestMapping(value = "/login_register/email_verification", method = RequestMethod.POST)
    public Object sendEmail(@RequestBody String request)
    {
        JsonObject obj = new JsonParser().parse(request).getAsJsonObject();
        String targetEmail = obj.get("email").toString();
        targetEmail = targetEmail.substring(1, targetEmail.length() - 1);
        int code = (int) (Math.random() * (99999 - 10000) + 10000);
        try {
            RegisterService.senEmail(targetEmail, String.valueOf(code));
            NormalResponse response = new NormalResponse();
            response.setCode("00");
            response.setDescription("Success.");
            return response;
        }catch (Exception e)
        {
            NormalResponse response = new NormalResponse();
            response.setCode("04");
            response.setDescription("Something wrong, please check the email address.");
            return response;
        }
    }

}
