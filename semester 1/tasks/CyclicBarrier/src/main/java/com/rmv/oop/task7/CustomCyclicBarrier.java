package com.rmv.oop.task7;

public class CustomCyclicBarrier {

    private int initialParties;
    private int partiesAwait;
    private Runnable cyclicBarrrierEvent;

    public CustomCyclicBarrier(int parties, Runnable cyclicBarrrierEvent) {
        initialParties = parties;
        partiesAwait = parties;
        this.cyclicBarrrierEvent = cyclicBarrrierEvent;
    }

    public synchronized void await() throws InterruptedException {
        partiesAwait--;
        if (partiesAwait > 0) {
            this.wait();
        }
        else {
            //This makes it cyclic
            partiesAwait = initialParties;
            notifyAll();
            cyclicBarrrierEvent.run();
        }
    }
}
