package com.hku.projectapi.Programming.Java;

import com.hku.projectapi.Beans.Programming.CompileResult;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Compile the Java class
 */
public class JavaCompilers
{
    public static CompileResult doCompile(String filePath)
    {
        PrintStream oldStream = System.out;
        ByteArrayOutputStream toString = new ByteArrayOutputStream(1024);
        PrintStream stringStream = new PrintStream(toString);
        System.setErr(stringStream);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            return new CompileResult(false, "");
        }

        int compilationResult = compiler.run(null, null, null, filePath);
        if (compilationResult != 0) {
            String error = toString.toString();
            System.setErr(oldStream);
            return new CompileResult(false, error);
        }
        else{
//            String error = toString.toString();
//            System.out.println(error);
            System.setErr(oldStream);
            return new CompileResult(true, "");
        }
    }
    public static boolean compileJava(String filePath) {
        PrintStream oldStream = System.out;
        ByteArrayOutputStream toString = new ByteArrayOutputStream(1024);
        PrintStream stringStream = new PrintStream(toString);
        System.setErr(stringStream);
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        if (compiler == null) {
            return false;
        }

        int compilationResult = compiler.run(null, null, null, filePath);
        if (compilationResult != 0) {
            String error = toString.toString();
            System.out.println(error + " AAA");
            return false;
        }
        else{
//            String error = toString.toString();
//            System.out.println(error);
        }
        return true;
    }

    // Test compile
    public static void main(String[] args)
    {
        JavaCompilers.compileJava("C:\\Users\\Tian Yu\\UserCodes\\33bed5eab0414800b3fd18ddb7290ab7\\33bed5eab0414800b3fd18ddb7290ab7.java");
    }
}
