package com.hku.projectapi.Programming.Util;

/**
 * For the uploaded codes, add the dependencies.
 */

public class AddPreCodes
{
    private static final String JavaPrefix = "import java.util.*;\nimport java.math.*;\n";

    public static String addCode(String originCode, String language, String uuid)
    {
        uuid = "a" + uuid;
        if(language.equals("Java"))
        {
            // Replace the class name with the uuid
            originCode = originCode.substring(0, 13) + uuid + originCode.substring(21);
            originCode = JavaPrefix + originCode;
        }
        return originCode;
    }

    public static void main(String[] args)
    {
        String code = "public class Solution{123\n";
        System.out.println(code.substring(21));
    }
}
