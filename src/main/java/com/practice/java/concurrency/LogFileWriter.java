package com.practice.java.concurrency;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
public class LogFileWriter {
    public static class LogTask implements Callable<Void> {
        private String logMessage;

        public LogTask(String logMessage) {
            this.logMessage = logMessage;
        }

        @Override
        public Void call() throws IOException {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter("logFile.txt", true))) {
                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(new java.util.Date());
                writer.write("["+timeStamp+"]" + logMessage);
                writer.newLine();
            }
            return null;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException{
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Void>> futures = new ArrayList<>();

        futures.add(executor.submit(new LogTask("Thread 1 started")));
        futures.add(executor.submit(new LogTask("Thread 2 started")));
        futures.add(executor.submit(new LogTask("Thread 3 started")));

        for(Future<Void> future : futures) {
            future.get();
        }

        executor.shutdown();
    }
}
