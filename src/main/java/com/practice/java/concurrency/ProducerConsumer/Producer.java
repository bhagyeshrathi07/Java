package com.practice.java.concurrency.ProducerConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable {

    private BlockingQueue<Integer> queue;
    private final int poisonPill;
    private final int poisonPillPerProducer;

    public Producer(BlockingQueue<Integer> queue, int poisonPill, int poisonPillPerProducer) {
        this.queue = queue;
        this.poisonPill = poisonPill;
        this.poisonPillPerProducer = poisonPillPerProducer;
    }

    @Override
    public void run() {
        try {
            generate();
        } catch (InterruptedException exc) {
            Thread.currentThread().interrupt();
        }
    }

    public void generate() throws InterruptedException{
        for(int i = 0; i < 100; i++) {
            queue.put(ThreadLocalRandom.current().nextInt(100));
        }

        for(int j = 0; j < poisonPillPerProducer; j++) {
            queue.put(poisonPill);
        }
    }
}
