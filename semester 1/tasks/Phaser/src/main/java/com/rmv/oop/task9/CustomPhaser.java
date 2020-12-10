package com.rmv.oop.task9;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomPhaser {

    private int initialParties;
    private int partiesAwait;
    private int phase;

    public CustomPhaser(int parties) {
        initialParties = parties;
        partiesAwait = parties;
        phase = 0;
    }

    public synchronized void register() {
        initialParties++;
        partiesAwait++;
    }

    public int getPhase() {
        return phase;
    }

    public synchronized void arriveAndAwaitAdvance() {
        partiesAwait--;
        if (partiesAwait > 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                log.error(e.toString());
            }
        } else {
            partiesAwait = initialParties;
            phase++;
            notifyAll();
        }
    }

    public synchronized void arriveAndDeregister() {
        initialParties = 0;
        partiesAwait = 0;
    }
}
