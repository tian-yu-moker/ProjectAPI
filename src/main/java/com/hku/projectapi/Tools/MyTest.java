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
//        Date date = new Date();
//        Timestamp time = new Timestamp(date.getTime());
//        System.out.println(time + " " + new Timestamp(date.getTime() - 30000));
//        RegisterService.senEmail("1948976547@qq.com", "12345");
        System.out.println();
        JwtUtil.updateToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJhdWQiOiIzZTdlMjM4NS03OTU4LTRlZDAtYTk1My1mYjRiMmUzZmUzZjMiLCJleHAiOjE2NTUzNDcwMDksImluZm8iOnsicGFzcyI6ImFkbWluIiwidXNlcm5hbWUiOiJ0b20ifX0.nJp3pjf2cb5n-dV4dr4aud3O2KVxOHrWZHCjj5EFmNI");
    }
}
