package com.practice.java.concurrency;

public class ThreadLifeCycle implements Runnable{

    public void run() {
        // code to be executed in thread
        System.out.println("MyThread!");
    }
    public static void main(String[] args) {
        Runnable runnable = new ThreadLifeCycle();
        Thread thread = new Thread(runnable);
        System.out.println(thread.getState() + " before start");
        thread.start();
        System.out.println(thread.getState() + " after start");
    }
}
