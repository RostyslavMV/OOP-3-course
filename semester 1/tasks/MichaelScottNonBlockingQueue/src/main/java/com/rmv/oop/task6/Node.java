package com.rmv.oop.task6;

import java.util.concurrent.atomic.AtomicReference;

public class Node<E> {
        E data;
        AtomicReference<Node> next;

        Node() {
            this(null);
        }

        Node(E data) {
            this.data = data;
            this.next = new AtomicReference<>();
        }
}
