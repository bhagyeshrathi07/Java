package com.practice.java.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeSingleton {

    private static volatile ThreadSafeSingleton instance;
    private static Lock lock = new ReentrantLock();

    private ThreadSafeSingleton() {

    }

    public static ThreadSafeSingleton getInstance() {
        if(instance == null) {
            try {
                lock.lock();
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }
}
