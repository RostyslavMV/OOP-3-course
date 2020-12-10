package com.rmv.oop.task5;

import com.rmv.oop.task5.model.LockFreeSkipList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        LockFreeSkipList<Integer> list = new LockFreeSkipList<>();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                list.add(i);
            }
        });
        thread.start();
        for (int i = 500; i < 1000; i++) {
            list.add(i);
        }
        try {
            thread.join();
        }
        catch (InterruptedException e){
            log.error(e.toString());
        }
        for (int i=0;i<1000;i++){
           if(!list.contains(i)){
               System.out.println("GG");
           }
        }

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                list.remove(i);
            }
        });
        thread1.start();
        for (int i = 500; i < 1000; i++) {
            list.remove(i);
        }
        try {
            thread1.join();
        }
        catch (InterruptedException e){
            log.error(e.toString());
        }
        for (int i=0;i<1000;i++){
            if(list.contains(i)){
                System.out.println("GG");
            }
        }
    }
}
