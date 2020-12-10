package com.rmv.oop.task3;

import com.rmv.oop.task3.service.ThreadGroupInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:threadGroupInfo.properties")
public class Main implements CommandLineRunner {

    @Autowired
    private ThreadGroupInfoService threadGroupInfoService;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        ThreadGroup threadGroup1 = new ThreadGroup("Thread Group 1");
        ThreadGroup threadGroup2 = new ThreadGroup(threadGroup1, "Thread Group 2");
        ThreadGroup threadGroup3 = new ThreadGroup(threadGroup2, "Thread Group 3");

        new Thread(threadGroup1, () -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(threadGroup1, () -> {
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(threadGroup1, () -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(threadGroup2, () -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(threadGroup2, () -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(threadGroup3, () -> {
            try {
                Thread.sleep(7000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(threadGroup3, () -> {
            try {
                Thread.sleep(12000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        threadGroupInfoService.threadsInfo(threadGroup1);
        threadGroupInfoService.threadsInfo(threadGroup2);
        threadGroupInfoService.threadsInfo(threadGroup3);
    }
}
