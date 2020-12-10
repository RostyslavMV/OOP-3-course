package com.rmv.oop.task4.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.rmv.oop.task4.model.TestClass2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:class.properties")
class ClassInfoServiceTest {

    @InjectMocks
    private ClassInfoService classInfoService;

    private String filePath1 =
            "D:/Users/Rostyslav/IdeaProjects/ClassInfo/src/main/java/com/rmv/oop/task4/model/TestClass1.class";

    private String classWithPackage1 = "com.rmv.oop.task4.model.TestClass1";

    private String filePath2 =
            "D:/Users/Rostyslav/IdeaProjects/ClassInfo/src/main/java/com/rmv/oop/task4/model/TestClass2.class";

    private String classWithPackage2 = "com.rmv.oop.task4.model.TestClass2";

    private Class loadedClass = TestClass2.class;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void printClassInfo() {
        classInfoService.printClassInfo(filePath2, classWithPackage2);
        String outContentString = outputStreamCaptor.toString();
        assertTrue(outContentString.contains(
                "D:/Users/Rostyslav/IdeaProjects/ClassInfo/src/main/java/com/rmv/oop/task4" + "/model/TestClass2" +
                        ".class"));
        assertTrue(outContentString.contains("public com.rmv.oop.task4.model.TestClass2"));
        assertTrue(outContentString.contains("private com.rmv.oop.task4.model.TestClass1 testClass1"));
        assertTrue(outContentString.contains("private java.lang.Boolean aBoolean"));
        assertTrue(outContentString.contains("public com.rmv.oop.task4.model.TestClass2"));
        assertTrue(outContentString.contains(" Parameters types: com.rmv.oop.task4.model.TestClass1, java.lang.Boolean"));
        assertTrue(outContentString.contains("public setTestClass1"));
        assertTrue(outContentString.contains("Parameters types: com.rmv.oop.task4.model.TestClass1"));

        classInfoService.printClassInfo(filePath1, classWithPackage1);
        outContentString = outputStreamCaptor.toString();
        assertTrue(outContentString.contains("public com.rmv.oop.task4.model.TestClass1"));
        assertTrue(outContentString.contains("private java.lang.String name"));
        assertTrue(outContentString.contains("private int number"));
        assertTrue(outContentString.contains("Parameters types: java.lang.String, int"));
    }

}
