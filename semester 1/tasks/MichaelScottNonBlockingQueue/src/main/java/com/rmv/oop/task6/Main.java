package com.rmv.oop.task6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        MichaelScottNonBlockingQueue<Integer> queue = new MichaelScottNonBlockingQueue<>();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                queue.enqueue(i);
            }
        });
        thread.start();
        for (int i = 500; i < 1000; i++) {
            queue.enqueue(i);
        }
        try {
            thread.join();
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                queue.dequeue();
            }
        });
        thread2.start();
        for (int i = 500; i < 1000; i++) {
            System.out.println(queue.dequeue());
        }
        try {
            thread2.join();
        } catch (InterruptedException e) {
            log.error(e.toString());
        }
    }
}
