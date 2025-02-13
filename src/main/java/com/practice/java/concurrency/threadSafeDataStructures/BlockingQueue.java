package com.practice.java.concurrency.threadSafeDataStructures;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//IMPLEMENTATION 1: Using Condition Variables

public class BlockingQueue<T> {
    private final Queue<T> queue;
    private final int capacity;
    private Lock lock;
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public BlockingQueue(int maxCapacity) {
        this.queue = new LinkedList<>();
        this.capacity = maxCapacity;
        this.lock = new ReentrantLock();
    }

    public T take() throws InterruptedException{
        lock.lock();
        try {
            while(queue.isEmpty()) {
                notEmpty.await();
            }
            T element = queue.poll();
            notFull.signal();
            return element;
        } finally {
            lock.unlock();
        }
    }

    public void put(T message) throws InterruptedException{
        lock.lock();
        try {
            while(queue.size() >= capacity) {
                notFull.await();
            }
            queue.offer(message);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }
}

// IMPLEMENTATION 2 : Using synchronized methods

//public class BlockingQueue<T> {
//    private final Queue<T> queue;
//    private final int capacity;
//
//    public BlockingQueue(int maxCapacity) {
//        this.queue = new LinkedList<>();
//        this.capacity = maxCapacity;
//    }
//
//    public synchronized T take() throws InterruptedException{
//        while(queue.isEmpty()) {
//            System.out.println("Queue is empty. Thread" + Thread.currentThread().getName() + " is waiting");
//            wait();
//        }
//        T message = queue.poll();
//        System.out.println("Thread " + Thread.currentThread().getName() + " pulled a message: " + message);
//        notify();
//        return message;
//    }
//
//    public synchronized void put(T message) throws InterruptedException {
//        while(queue.size() == this.capacity) {
//            System.out.println("Queue is full " + Thread.currentThread().getName() + " is waiting");
//            wait();
//        }
//        queue.offer(message);
//        System.out.println("Thread " + Thread.currentThread().getName() + " published a message: " + message);
//        notify();
//    }
//}