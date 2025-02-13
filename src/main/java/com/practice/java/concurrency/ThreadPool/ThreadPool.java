package com.practice.java.concurrency.ThreadPool;

import java.util.List;
import java.util.concurrent.*;
import java.util.ArrayList;

public class ThreadPool {
    private final List<Worker> threads = new ArrayList<>();
    private final TaskQueue queue = new TaskQueue();

    public ThreadPool(int nThreads) {
        for(int i = 0; i < nThreads; i++) {
            Worker worker = new Worker(queue);
            worker.start();
            threads.add(worker);
        }
    }

    public void execute(Runnable task) {
        queue.submit(task);
    }
}


class Worker extends Thread{
    private final TaskQueue taskQueue;

    public Worker(TaskQueue queue) {
        this.taskQueue = queue;
    }

    public void run() {
        while(true) {
            try {
                Runnable task = taskQueue.take();
                task.run();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


class TaskQueue {
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();

    public void submit(Runnable  task) {
        try {
            queue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Runnable take() throws InterruptedException{
        return queue.take();
    }
}