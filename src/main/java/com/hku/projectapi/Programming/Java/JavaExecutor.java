package com.hku.projectapi.Programming.Java;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Executor of Java
 * Based on the reflex mechanism
 */
public class JavaExecutor
{

    public static void doExecute(URL url, String filePath) throws Exception
    {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        try{
//            ClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url}, systemClassLoader);
//            Class<?> cls = classLoader.loadClass("Solutions");
//            System.out.println("AAAAAA");
//            Object instance = cls.newInstance();
//            Method method = cls.getDeclaredMethod("solution", String.class);
//            int result = (int) method.invoke(instance);
//            System.out.println(result + " AAA");
        } catch (Exception e){
            // System.out.println(e.getMessage());

        }
//        ClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url}, systemClassLoader);
//        Class<?> cls = classLoader.loadClass("Solution");
//        System.out.println("AAAAAA");
//        Object instance = cls.newInstance();
//        Method[] methods = cls.getDeclaredMethods();
//        for (Method method : methods) {
//            System.out.println(method.getName());
//        }
//        Method method = cls.getDeclaredMethod("solution", String.class);
//        int result = (int) method.invoke(instance);
//        System.out.println(result + " AAA");

        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{url});
        Class<?> cls = Class.forName(filePath, true, classLoader);
        Object instance = cls.newInstance();
        System.out.println();
        Method method = cls.getDeclaredMethod("twoSum", int[].class, int.class);
        method.setAccessible(true);
        int[] result = (int[]) method.invoke(instance, new int[]{1,2, 3}, 4);

        System.out.println(result[1] + " AAA");
    }

    public static void main(String args[]) throws Exception {
        System.gc();
        String path = "C:\\Users\\Tian Yu\\UserCodes\\5c1dae37561a4b57b73f8f6a5ed1ea62123@qq.com";
        JavaCompilers.doCompile(path + "/Solutions2.java");
        // File file = new File(path + "\\Solutions.class");
////        URI uri = file.toURI();
        URL url = new URL("file:///" + path + "/");
//        System.out.println("file:///" + path + "/");
//
        JavaExecutor.doExecute(url, "Solutions2");
//        Class.forName("Solutions", true, new MyClassLoad());
        int[] a = new int[]{1,2, 3};
        System.out.println("file:///" + path + "/");
    }
}
