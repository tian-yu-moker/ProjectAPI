package com.hku.projectapi.Tools;

import com.hku.projectapi.Service.RegisterService;

import java.sql.Timestamp;
import java.util.Date;

public class MyTest
{
    public static void main(String[] args)
    {
        String date1 = "2022-06-12 16:25:38";
        String date2 = "2022-06-12 16:25:38";
        int compare = date1.compareTo(date2);
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());
        System.out.println(time + " " + new Timestamp(date.getTime() - 30000));
        RegisterService.senEmail("1948976547@qq.com", "12345");
    }
}
