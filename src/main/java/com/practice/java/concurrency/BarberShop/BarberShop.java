package com.practice.java.concurrency.BarberShop;

import java.util.Random;
import java.util.concurrent.*;

public class BarberShop {
    private Semaphore accessChair = new Semaphore(1);
    private Semaphore customerReady = new Semaphore(0);
    private Semaphore barberReady = new Semaphore(0);
    private int capacity;
    private int waitingCustomers;

    public BarberShop(int capacity) {
        this.capacity = capacity;
        this.waitingCustomers = 0;
    }

    public void getHaircut(int customerId) {
        try {
            accessChair.acquire();
            if(waitingCustomers < capacity) {
                waitingCustomers++;
                System.out.println("Customer " + customerId + " is waiting. (Waiting: " + waitingCustomers + ")");
                accessChair.release();
                customerReady.release();
                barberReady.acquire();
                System.out.println("Customer " + customerId + " is getting a haircut.");
            }
            else {
                System.out.println("Customer " + customerId + " left (No seats available)");
                accessChair.release();
            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void barberWork() {
        while(true) {
            try {
                customerReady.acquire();
                accessChair.acquire();
                waitingCustomers--;
                System.out.println("Barber is cutting hair (" + waitingCustomers + " customers waiting.)");
                accessChair.release();
                Thread.sleep(2000);
                barberReady.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        int totalChairs = 5;

         BarberShop shop = new BarberShop(totalChairs);

         Thread barberThread = new Thread(shop::barberWork);
         barberThread.start();

         ExecutorService executor = Executors.newFixedThreadPool(1);
         Random random = new Random();

         for(int i = 1; i <= 10; i++) {
             int customerId = i;
             executor.execute(() -> shop.getHaircut(customerId));

             try {
                 Thread.sleep(random.nextInt(1000));
             }catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         executor.shutdown();
    }
}
