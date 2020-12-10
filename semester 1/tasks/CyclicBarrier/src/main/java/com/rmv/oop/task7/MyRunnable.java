package com.rmv.oop.task7;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyRunnable implements Runnable {

    private CustomCyclicBarrier cyclicBarrierCustom;

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() +
                " is waiting for all other threads to reach common barrier point");
        try {
            Thread.sleep(1000);
            cyclicBarrierCustom.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("As all threads have reached common barrier point "
                + Thread.currentThread().getName() +
                " has been released");
    }
}

