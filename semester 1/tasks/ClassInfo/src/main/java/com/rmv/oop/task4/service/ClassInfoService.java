package com.rmv.oop.task4.service;

import org.springframework.stereotype.Service;
import com.rmv.oop.task4.classLoader.CustomClassLoader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class ClassInfoService {

    public void printClassInfo(String classFilePath, String classWithPackage) {
        System.out.println("Class file path: " + classFilePath +
                ". Class with package: " + classWithPackage);
        CustomClassLoader customClassLoader = new CustomClassLoader(classFilePath);
        Class loadedClass;

        try {
            loadedClass = customClassLoader.loadClass(classWithPackage);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            return;
        }

        int classModifiers = loadedClass.getModifiers();
        System.out.println(Modifier.toString(classModifiers) + " " + loadedClass.getName());
        printFields(loadedClass);
        printConstructors(loadedClass);
        printMethods(loadedClass);
    }

    private void printFields(Class aClass) {
        if (aClass.getDeclaredFields().length > 0) {
            System.out.println("-------------");
            System.out.println("Fields:");
            for (Field field : aClass.getDeclaredFields()) {
                int modifiers = field.getModifiers();
                System.out.println(Modifier.toString(modifiers) + " " +
                        field.getType().getName() + " " +field.getName());
            }
        }
    }

    private void printConstructors(Class aClass) {
        if (aClass.getConstructors().length > 0) {
            System.out.println("-------------");
            System.out.println("Constructors:");
            for (Constructor constructor : aClass.getConstructors()) {
                int modifiers = constructor.getModifiers();
                System.out.println(Modifier.toString(modifiers) + " " + constructor.getName());
                System.out.println(" Parameters types: " +
                        Arrays.stream(constructor.getParameterTypes())
                                .map(Class::getName)
                                .collect(Collectors.joining(", ")));
                System.out.println("---");
            }
        }
    }

    private void printMethods(Class aClass) {
        if (aClass.getMethods().length > 0) {
            System.out.println("-------------");
            for (Method method : aClass.getMethods()) {
                int modifiers = method.getModifiers();
                System.out.println(Modifier.toString(modifiers) + " " + method.getName());
                System.out.println(" Parameters types: " +
                        Arrays.stream(method.getParameterTypes())
                                .map(Class::getName)
                                .collect(Collectors.joining(", ")));
                System.out.println("---");
            }
        }
    }
}
