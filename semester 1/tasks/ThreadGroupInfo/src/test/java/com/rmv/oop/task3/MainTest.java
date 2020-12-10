package com.rmv.oop.task3;

import com.rmv.oop.task3.service.ThreadGroupInfoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class MainTest {
    private final static String filePath = "fPath";
    private final static String classWithPackage = "classWithPackage";

    @Autowired
    private Main main;

    @MockBean
    private ThreadGroupInfoService threadGroupInfoService;

    @BeforeAll
    public static void init() {
        System.setProperty("millisInterval", "2000");
    }

    @Test
    void main() {
        System.out.println("main");
        String[] args = new String[0];
        Main.main(args);
    }

}