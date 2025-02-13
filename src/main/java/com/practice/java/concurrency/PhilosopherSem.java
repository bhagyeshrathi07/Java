package com.practice.java.concurrency;
import java.util.concurrent.Semaphore;

public class PhilosopherSem implements Runnable{
    private int id;
    private Semaphore forks;

    public PhilosopherSem(int id, Semaphore forks) {
        this.id = id;
        this.forks = forks;
    }

    public void run() {
        while(true) {
            think();
            pickUpForks();
            eat();
            putDownForks();
        }
    }

    public void think() {
        System.out.println("Philosopher " + id + ":" + " Thinking");
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
    }

    public void pickUpForks() {
        try {
            forks.acquire(2);
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
    }

    public void eat() {
        System.out.println("Philosopher " + id + ":" + " Eating");
        try{
            Thread.sleep((long)(Math.random() * 1000));
        } catch (InterruptedException exc) {
            exc.printStackTrace();
        }
    }

    public void putDownForks() {
        forks.release(2);
    }

}
