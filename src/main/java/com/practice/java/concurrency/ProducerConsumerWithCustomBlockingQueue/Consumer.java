package com.practice.java.concurrency.ProducerConsumerWithCustomBlockingQueue;

public class Consumer implements Runnable{
    private final BlockingQueue<Integer> queue;
    private final int poisonPill;

    public Consumer(BlockingQueue<Integer> queue, int poisonPill) {
        this.queue = queue;
        this.poisonPill = poisonPill;
    }

    public void run() {
        try {
            while(true) {
                Integer message = queue.take();
                if(message.equals(poisonPill)) {
                    System.out.println("Thread " + Thread.currentThread().getName() + " stopping due to poisonPill");
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "consumed : " + message);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
