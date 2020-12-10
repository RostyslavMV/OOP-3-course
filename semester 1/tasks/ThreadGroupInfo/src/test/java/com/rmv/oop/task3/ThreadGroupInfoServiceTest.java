package com.rmv.oop.task3;

import com.rmv.oop.task3.service.ThreadGroupInfoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class ThreadGroupInfoServiceTest {

    @InjectMocks
    private ThreadGroupInfoService threadGroupInfoService;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void threadsInfo() {
        ThreadGroup threadGroup = new ThreadGroup("ThreadGroup");
        ThreadGroup subGroup = new ThreadGroup(threadGroup, "SubGroup");
        new Thread(threadGroup, () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(threadGroup, () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(subGroup, () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        threadGroupInfoService.threadsInfo(threadGroup);
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        String outContentString = outputStreamCaptor.toString();
        assertTrue(outContentString.contains("ThreadGroup"));
        assertTrue(outContentString.contains("Thread-"));
        assertTrue(outContentString.contains("SubGroup"));
    }
}
