package com.fintech.jvm;

import java.util.concurrent.CompletableFuture;

public class AsyncDemo {
    public static void main(String[] args) {

        System.out.println("1. Ordering coffee from the API...");


        CompletableFuture<String> coffeeFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000); // database delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "☕ Hot Caramel Latte";
        });

        System.out.println("2. Main thread is free! Checking emails, scrolling Twitter...");

        // When the background task finally finishes
        coffeeFuture.thenAccept(coffee -> {
            System.out.println("3. Callback triggered! Received: " + coffee);
        });

        // Keep the main thread alive for a second so the background task can finish printing
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
