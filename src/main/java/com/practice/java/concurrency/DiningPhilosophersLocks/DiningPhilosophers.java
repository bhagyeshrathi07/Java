package com.practice.java.concurrency.DiningPhilosophersLocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DiningPhilosophers {

    public static void main(String[] args) throws InterruptedException {
        int numOfPhilosophers = 5;
        Philosopher[] philosophers = new Philosopher[numOfPhilosophers];
        Lock[] forks = new Lock[numOfPhilosophers];

        for(int i = 0; i < numOfPhilosophers; i++) {
            forks[i] = new ReentrantLock();
        }

        for(int i = 0; i < numOfPhilosophers; i++) {
            philosophers[i] = new Philosopher(i, forks[i], forks[(i+1) % numOfPhilosophers]);
            new Thread(philosophers[i]).start();
        }
    }
}