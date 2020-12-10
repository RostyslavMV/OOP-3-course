package com.rmv.oop.task7;

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
        CustomCyclicBarrier cyclicBarrierCustom = new CustomCyclicBarrier(3, new CustomCyclicBarrrierEvent());
        MyRunnable myRunnable = new MyRunnable(cyclicBarrierCustom);

        //Create and start 3 threads
        new Thread(myRunnable, "Thread-1").start();
        new Thread(myRunnable, "Thread-2").start();
        new Thread(myRunnable, "Thread-3").start();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("");
        //Create and start 3 more threads
        new Thread(myRunnable, "Thread-4").start();
        new Thread(myRunnable, "Thread-5").start();
        new Thread(myRunnable, "Thread-6").start();

        CustomCyclicBarrier cyclicBarrierCustom2 = new CustomCyclicBarrier(100, new CustomCyclicBarrrierEvent());
        MyOtherRunnable myOtherRunnable = new MyOtherRunnable(cyclicBarrierCustom2);
        for (int i = 0; i < 100; i++) {
            new Thread(myOtherRunnable, "Thread-" + i).start();
        }
    }
}
