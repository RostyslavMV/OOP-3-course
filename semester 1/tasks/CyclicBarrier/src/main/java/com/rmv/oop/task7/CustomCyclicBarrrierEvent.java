package com.rmv.oop.task7;

public class CustomCyclicBarrrierEvent implements Runnable {
    @Override
    public void run() {
        System.out.println("As all threads have reached common barrier point "
                + ", CustomCyclicBarrrierEvent has been triggered");
    }
}
