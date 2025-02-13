package com.practice.java.concurrency;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private int data = 0;

    public void read() {
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is reading " + data);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write (int newData) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " is writing ");
            data = newData;
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {
        ReadWriteLock sharedResource = new ReadWriteLock();

        // Create writer thread
        Thread writer = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharedResource.write(i);
                try {
                    Thread.sleep(100); // Simulate delay
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Writer");

        // Create multiple reader threads
        Thread reader1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharedResource.read();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Reader-1");

        Thread reader2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                sharedResource.read();
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Reader-2");

        // Start all threads
        writer.start();
        reader1.start();
        reader2.start();

        // Wait for threads to finish
        try {
            writer.join();
            reader1.join();
            reader2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
