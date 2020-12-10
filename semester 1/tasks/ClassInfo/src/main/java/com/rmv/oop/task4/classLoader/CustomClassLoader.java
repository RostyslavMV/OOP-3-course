package com.rmv.oop.task4.classLoader;

import lombok.AllArgsConstructor;

import java.io.*;

@AllArgsConstructor
public class CustomClassLoader extends ClassLoader {

    private String classFileName;

    @Override
    protected Class<?> findClass(String classWithPackage) {
        byte[] classBytes = loadClassFromFile(classFileName);

        return defineClass(classWithPackage,classBytes,0,classBytes.length);
    }

    private byte[] loadClassFromFile(String classFileName) {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(
                classFileName.replace('.', File.separatorChar) + ".class");

        byte[] buffer;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int nextValue;
        try {
            while ( (nextValue = inputStream.read()) != -1 ) {
                byteStream.write(nextValue);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        buffer = byteStream.toByteArray();
        return buffer;
    }
}
