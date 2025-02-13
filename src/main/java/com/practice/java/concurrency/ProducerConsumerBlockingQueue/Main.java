package com.practice.java.concurrency.ProducerConsumerBlockingQueue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    public static void main(String[] args) {
        int BOUND = 10;
        int N_PRODUCERS = 4;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
        int poisonPill = Integer.MAX_VALUE;
        int poisonPIllPerProducer = N_CONSUMERS / N_PRODUCERS;
        int mod = N_CONSUMERS % N_PRODUCERS;

        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(BOUND);

        for(int i = 1; i < N_PRODUCERS; i++) {
            new Thread(new Producer(queue, poisonPill, poisonPIllPerProducer)).start();
        }

        for(int j = 0; j < N_CONSUMERS; j++) {
            new Thread(new Consumer(queue, poisonPill)).start();
        }

        new Thread(new Producer(queue, poisonPill, poisonPIllPerProducer + mod)).start();
    }
}
