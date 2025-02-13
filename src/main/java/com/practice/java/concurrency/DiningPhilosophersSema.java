package com.practice.java.concurrency;

import java.util.concurrent.Semaphore;

public class DiningPhilosophersSema {

    public static void main(String[] args) {
        int numOfPhilosophers = 5;
        Semaphore forks = new Semaphore(5);
        PhilosopherSem[] philosophers = new PhilosopherSem[numOfPhilosophers];

        for(int i = 0; i < numOfPhilosophers; i++) {
            philosophers[i] = new PhilosopherSem(i, forks);
            new Thread(philosophers[i]).start();
        }
    }
}
