package com.rmv.oop.task4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import com.rmv.oop.task4.service.ClassInfoService;

@SpringBootApplication
@PropertySource("classpath:class.properties")
public class Main implements CommandLineRunner {

    @Autowired
    ClassInfoService classInfoService;

    @Value("${filePath1}")
    private String filePath1;

    @Value("${filePath2}")
    private String filePath2;

    @Value("${classWithPackage1}")
    private String classWithPackage1;

    @Value("${classWithPackage2}")
    private String classWithPackage2;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        classInfoService.printClassInfo(filePath1, classWithPackage1);
        classInfoService.printClassInfo(filePath2, classWithPackage2);
    }
}
