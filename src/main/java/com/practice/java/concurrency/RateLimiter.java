package com.practice.java.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class RateLimiter {
    private final int capacity;
    private final int refillRate;
    private int tokens;
    private final ReentrantLock lock = new ReentrantLock();

    public RateLimiter(int capacity, int refillRate) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.tokens = capacity;

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::refill, 1, 1, TimeUnit.SECONDS);
    }

    public boolean tryAcquire() {
        lock.lock();
        try {
            if (tokens > 0) {
                tokens--;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    public void refill() {
        lock.lock();
        try {
            tokens = Math.min(capacity, tokens + refillRate);
        } finally {
            lock.unlock();
        }
    }

    public int getAvailableTokens() {
        return this.tokens;
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter(5, 1);

        Runnable task = () -> {
            if(rateLimiter.tryAcquire()) {
                System.out.println("Request Allowed: " + Thread.currentThread().getName());
            } else {
                System.out.println(Thread.currentThread().getName() + " request Declined");
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(3);
        for(int i = 0; i < 20; i++) {
            executor.execute(task);
        }
        executor.shutdown();
    }
}
