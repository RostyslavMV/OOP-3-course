package com.rmv.oop.task8;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyRunnable implements Runnable {
    CustomLock customLock;

    public MyRunnable(CustomLock customLock) {
        this.customLock = customLock;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()
                + " is Waiting to acquire CustomLock");
        customLock.lock();
        System.out.println(Thread.currentThread().getName()
                + " has acquired LockCustom.");
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName()
                    + " is sleeping.");
        } catch (InterruptedException e) {
           log.error(e.toString());
        }
        customLock.unlock();
        System.out.println(Thread.currentThread().getName()
                + " has released CustomLock.");
    }
}
