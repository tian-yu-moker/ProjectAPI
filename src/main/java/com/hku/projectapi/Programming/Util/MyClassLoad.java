package com.hku.projectapi.Programming.Util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClassLoad extends ClassLoader{

    public MyClassLoad()
    {

    }

    @Override
    protected Class<?> findClass(String className)
            throws ClassNotFoundException {
        byte[] cLassBytes = null;
        //Java 7有下列ＡＰＩ
        try {
            Path path = Paths.get(new URI("file:///C:/Users/tianyu3/UserCodes/test/Solutions.class"));
            cLassBytes = Files.readAllBytes(path);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        Class myClass = defineClass(cLassBytes, 0, cLassBytes.length);
        return myClass;
    }
}
