package com.practice.java.concurrency;
import java.util.concurrent.locks.Lock;

public class Philosopher implements Runnable{
   private int id;
   private Lock leftFork;
   private Lock rightFork;

   public Philosopher(int id, Lock left, Lock right) {
       this.id = id;
       this.leftFork = left;
       this.rightFork = right;
   }

   public void run() {
       think();
       pickUpForks();
       eat();
       putDownForks();
   }

   public void think() {
       System.out.println("Philosopher " + this.id + " is Thinking.");
       try {
           Thread.sleep((long) (Math.random() * 1000));
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }

   public void pickUpForks() {
       if(this.id % 2 == 0) {
           leftFork.lock();
           rightFork.lock();
       } else {
           rightFork.lock();
           leftFork.lock();
       }
   }

   public void eat() {
       System.out.println("Philosopher " + this.id + " is eating.");
       try {
           Thread.sleep((long)(Math.random() * 1000));
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
   }

   public void putDownForks() {
        rightFork.unlock();
        leftFork.unlock();
   }

}


// private int id;
//    private Lock leftFork;
//    private Lock rightFork;
//
//    public Philosopher(int id, Lock leftFork, Lock rightFork) {
//        this.id = id;
//        this.leftFork = leftFork;
//        this.rightFork = rightFork;
//    }
//
//    public void run() {
//        while(true) {
//            think();
//            pickUpForks();
//            eat();
//            putDownForks();
//        }
//    }
//
//    public void think() {
//        System.out.println("Philosopher " + id + ":" + " Thinking");
//        try {
//            Thread.sleep((long) (Math.random() * 1000));
//        } catch (InterruptedException exc) {
//            exc.printStackTrace();
//        }
//    }
//
//    public void pickUpForks() {
//        if(id % 2 == 0) {
//            leftFork.lock();
//            rightFork.lock();
//        }
//        else {
//            rightFork.lock();
//            leftFork.lock();
//        }
//    }
//
//    public void eat() {
//        System.out.println("Philosopher " + id + ":" + " Eating");
//        try{
//            Thread.sleep((long)(Math.random() * 1000));
//        } catch (InterruptedException exc) {
//            exc.printStackTrace();
//        }
//    }
//
//    public void putDownForks() {
//        rightFork.unlock();
//        leftFork.unlock();
//    }