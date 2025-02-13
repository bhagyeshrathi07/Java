package com.practice.java.concurrency.ProducerConsumer;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{
    private BlockingQueue<Integer> queue;
    private final int poisonPill;

    public Consumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    @Override
    public void run() {
        try {
            while(true) {
                Integer number = queue.take();
                if(number.equals(poisonPill)) {
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " result: " + number);
            }
        } catch(InterruptedException exc) {
            Thread.currentThread().interrupt();
        }
    }
}
