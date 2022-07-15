package com.hku.projectapi.Programming.Util;

/**
 * For the uploaded codes, add the dependencies.
 */

public class AddPreCodes
{
    private static final String JavaPrefix = "import java.util.*;\nimport java.math.*;\nimport com.hku.projectapi.Programming.Dependencies.*;\n";

    public static String addCode(String originCode, String language)
    {
        if(language.equals("Java"))
        {
            originCode = JavaPrefix + originCode;
        }
        return originCode;
    }
}
