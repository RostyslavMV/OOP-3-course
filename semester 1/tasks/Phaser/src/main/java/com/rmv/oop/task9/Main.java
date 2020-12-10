package com.rmv.oop.task9;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Phaser;

@SpringBootApplication
public class Main implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {

        CustomPhaser customPhaser = new CustomPhaser(1);
        System.out.println("new phaser with 1 registered unArrived parties"
                + " created and initial phase  number is 0 ");

        Thread customThread1 = new Thread(new MyRunnable(customPhaser, "first"), "Custom Thread-1");
        Thread customThread2 = new Thread(new MyRunnable(customPhaser, "second"), "Custom Thread-2");
        Thread customThread3 = new Thread(new MyRunnable(customPhaser, "third"), "Custom Thread-3");

        System.out.println("\n--------Phaser has started---------------");

        customThread1.start();
        customThread2.start();
        customThread3.start();

        int currentCustomPhase = customPhaser.getPhase();

        customPhaser.arriveAndAwaitAdvance();
        System.out.println("------Phase-" + currentCustomPhase + " has been COMPLETED----------");

        currentCustomPhase = customPhaser.getPhase();

        customPhaser.arriveAndAwaitAdvance();
        System.out.println("------Phase-" + currentCustomPhase + " has been COMPLETED----------");

        customPhaser.arriveAndDeregister();

        System.out.println('\n' + "---------Library version of phaser for comparison----------" + '\n');

        Phaser phaser = new Phaser(1);
        System.out.println("new phaser with 1 registered unArrived parties"
                + " created and initial phase  number is 0 ");

        Thread thread1 = new Thread(new MyRunnableLibraryPhaser(phaser, "first"), "Thread-1");
        Thread thread2 = new Thread(new MyRunnableLibraryPhaser(phaser, "second"), "Thread-2");
        Thread thread3 = new Thread(new MyRunnableLibraryPhaser(phaser, "third"), "Thread-3");

        System.out.println("\n--------Phaser has started---------------");

        thread1.start();
        thread2.start();
        thread3.start();

        int currentPhase = phaser.getPhase();

        phaser.arriveAndAwaitAdvance();
        System.out.println("------Phase-" + currentPhase + " has been COMPLETED----------");

        currentPhase = phaser.getPhase();
        phaser.arriveAndAwaitAdvance();
        System.out.println("------Phase-" + currentPhase + " has been COMPLETED----------");

        phaser.arriveAndDeregister();

        if (phaser.isTerminated())
            System.out.println("\nPhaser has been terminated");
    }
}
