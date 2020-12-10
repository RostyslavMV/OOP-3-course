package com.rmv.oop.task9;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Phaser;

@Slf4j
public class MyRunnable implements Runnable {

    CustomPhaser phaser;

    MyRunnable(CustomPhaser phaser, String name) {
        this.phaser = phaser;
        this.phaser.register(); //Registers/Add a new unArrived party to this phaser.
        System.out.println(name + " - New unarrived party has "
                + "been registered with phaser");
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +
                " - party has arrived and is working in "
                + "Phase-" + phaser.getPhase());
        phaser.arriveAndAwaitAdvance();

        //Sleep has been used for formatting output
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //------NEXT PHASE BEGINS------

        System.out.println(Thread.currentThread().getName() +
                " - party has arrived and is working in "
                + "Phase-" + phaser.getPhase());

        phaser.arriveAndAwaitAdvance();


        phaser.arriveAndDeregister();
    }

}