package com.rmv.oop.task8;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TicketRunnable implements Runnable {
    int ticketsAvailable = 2;
    CustomLock lockCustom;

    public TicketRunnable(CustomLock lockCustom) {
        this.lockCustom = lockCustom;
    }

    @Override
    public void run() {

        System.out.println("Waiting to book ticket for : " +
                Thread.currentThread().getName());

        lockCustom.lock();

        if (ticketsAvailable > 0) {
            System.out.println("Booking ticket for : " +
                    Thread.currentThread().getName());

            //Let's say system takes some time in booking ticket
            //(here we have taken 1 second time)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.error(e.toString());
            }

            ticketsAvailable--;
            System.out.println("Ticket booked for : " +
                    Thread.currentThread().getName());
            System.out.println("currently ticketsAvailable = " + ticketsAvailable);
        } else {
            System.out.println("Ticket NOT booked for : " +
                    Thread.currentThread().getName());
        }


        lockCustom.unlock();
    }
}
