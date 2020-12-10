package com.rmv.oop.task7;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MyOtherRunnable implements Runnable {

    private CustomCyclicBarrier cyclicBarrierCustom;

    @Override
    public void run() {
        try {
            System.out.println("Hello from " + Thread.currentThread().getName());
            cyclicBarrierCustom.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Goodbye from " + Thread.currentThread().getName());
    }
}
