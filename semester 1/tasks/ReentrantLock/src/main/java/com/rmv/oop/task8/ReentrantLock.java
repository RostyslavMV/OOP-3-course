package com.rmv.oop.task8;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReentrantLock implements CustomLock {
    private int lockHoldCount;
    private long idOfThreadCurrentlyHoldingLock;

    public ReentrantLock() {
        lockHoldCount = 0;
    }

    @Override
    public synchronized void lock() {
        if (lockHoldCount == 0) {
            idOfThreadCurrentlyHoldingLock = Thread.currentThread().getId();
        } else if (idOfThreadCurrentlyHoldingLock != Thread.currentThread().getId()) {
            try {
                wait();
                idOfThreadCurrentlyHoldingLock = Thread.currentThread().getId();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        lockHoldCount++;
    }

    @Override
    public synchronized void unlock() {
        if (lockHoldCount == 0) {
            throw new IllegalMonitorStateException();
        }
        lockHoldCount--;
        if (lockHoldCount == 0) {
            notify();
        }
    }

    @Override
    public synchronized boolean tryLock() {
        if (lockHoldCount == 0) {
            lock();
            return true;
        } else return false;
    }
}
