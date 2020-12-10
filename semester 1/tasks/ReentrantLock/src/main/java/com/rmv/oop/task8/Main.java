package com.rmv.oop.task8;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        CustomLock reentrantLock = new ReentrantLock();
//        MyRunnable myRunnable = new MyRunnable(reentrantLock);
//        new Thread(myRunnable, "Thread-1").start();
//        new Thread(myRunnable, "Thread-2").start();
        TicketRunnable ticketRunnable = new TicketRunnable(reentrantLock);
        new Thread(ticketRunnable, "Passenger1 Thread").start();
        new Thread(ticketRunnable, "Passenger2 Thread").start();
        new Thread(ticketRunnable, "Passenger3 Thread").start();
    }
}
