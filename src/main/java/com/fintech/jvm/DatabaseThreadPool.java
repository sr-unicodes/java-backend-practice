package com.fintech.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DatabaseThreadPool {
    public static void main(String[] args) {

        System.out.println("Starting Custom Thread Pool...");

        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 1; i <= 15; i++) {
            final int taskId = i;

            executor.submit(() -> {
                String threadName = Thread.currentThread().getName();
                System.out.println(threadName + " is updating user " + taskId + " in the database...");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(threadName + " finished user " + taskId);
            });
        }

        executor.shutdown();
        System.out.println("All tasks submitted. Main thread is free to do other things!");
    }
}
