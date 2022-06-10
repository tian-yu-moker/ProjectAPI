package com.hku.projectapi.Tools;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

public class UUidGenerator
{
    public static String getUUID32()
    {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    public static void main(String args[])
    {
        Date date = new Date();
        Timestamp timeStamp = new Timestamp(date.getTime());
        System.out.println(timeStamp);
    }
}
