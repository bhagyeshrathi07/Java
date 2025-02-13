package com.practice.java.concurrency.ProducerConsumerWithCustomBlockingQueue;

public class Main {

    public static void main(String[] args) {
        int BOUND = 10;
        int NUM_PRODUCER = 4;
        int NUM_CONSUMER = Runtime.getRuntime().availableProcessors();
        int poisonPill = Integer.MAX_VALUE;
        int poisonPillPerProducer = NUM_CONSUMER / NUM_PRODUCER;
        int mod = NUM_CONSUMER % NUM_PRODUCER;

        BlockingQueue<Integer> queue = new BlockingQueue<>(BOUND);

        for(int i = 1; i < NUM_PRODUCER; i++) {
            new Thread(new Producer(queue, poisonPill, poisonPillPerProducer)).start();
        }

        for(int j = 0; j < NUM_CONSUMER; j++) {
            new Thread(new Consumer(queue, poisonPill)).start();
        }

        new Thread(new Producer(queue, poisonPill, poisonPillPerProducer + mod)).start();
    }
}
