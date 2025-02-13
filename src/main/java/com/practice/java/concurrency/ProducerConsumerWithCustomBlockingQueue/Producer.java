package com.practice.java.concurrency.ProducerConsumerWithCustomBlockingQueue;

import java.util.concurrent.ThreadLocalRandom;

public class Producer implements Runnable{
    private final BlockingQueue<Integer> queue;
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
            produce();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void produce() throws InterruptedException{
        for(int i = 0; i < 10; i++) {
            queue.put(ThreadLocalRandom.current().nextInt(100));
        }

        for(int j = 0; j < poisonPillPerProducer; j++) {
            queue.put(poisonPill);
        }
    }
}
