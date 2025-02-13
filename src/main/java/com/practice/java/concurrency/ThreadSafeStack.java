package com.practice.java.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeStack<T> {
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> top;
    private final ReentrantLock lock = new ReentrantLock();

    public void push(T data) {
        lock.lock();
        try {
            Node<T> newNode = new Node<>(data);
            newNode.next = top;
            top = newNode;
        } finally {
            lock.unlock();
        }
    }

    public T pop() {
        lock.lock();
        try {
            if(top == null) {
                throw new IllegalStateException("Stack is Empty");
            }
            T value = top.data;
            top = top.next;
            return value;
        } finally {
            lock.unlock();
        }
    }

    public T peek() {
        lock.lock();
        try {
            if(top == null) {
                throw new IllegalStateException("Stack is Empty");
            }
            return top.data;
        } finally {
            lock.unlock();
        }
    }

    public boolean isEmpty() {
        lock.lock();
        try {
            return top == null;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ThreadSafeStack<Integer> stack = new ThreadSafeStack<>();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                stack.push(i);
                System.out.println(Thread.currentThread().getName() + " pushed " + i);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                stack.push(i);
                System.out.println(Thread.currentThread().getName() + " pushed " + i);
            }
        });

        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
