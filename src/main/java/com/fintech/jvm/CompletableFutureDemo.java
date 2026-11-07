package com.fintech.jvm;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {
    public static void main(String[] args) {

        System.out.println("Main Thread: Firing network calls...");
        long startTime = System.currentTimeMillis();

        CompletableFuture<String> flightTask = CompletableFuture.supplyAsync(() -> {
            try { Thread.sleep(1000); } catch (Exception e) {}
            return "Flight: AirIndia-202";
        });

        CompletableFuture<String> hotelTask = CompletableFuture.<String>supplyAsync(() -> {
            try { Thread.sleep(2000); } catch (Exception e) {}

            System.out.println("Hotel API Thread: CRASHING!");
            throw new RuntimeException("Hotel Server 500 Internal Error");

        })
                .exceptionally(ex -> {
                    System.out.println("ERROR CAUGHT: " + ex.getMessage());
                    return "Hotel: Manual Booking Required";
                });
        flightTask.thenCombine(hotelTask,(flight,hotel)->{
            return flight+ " | " + hotel;
        })
                .thenAccept(result ->{
                    System.out.println("dashboard:" + result);
                })   ;

        System.out.println("Main Thread: I am not blocked! I can do other things while APIs load.");

        try { Thread.sleep(3000); } catch (Exception e) {}

        System.out.println("Total Time: " + (System.currentTimeMillis() - startTime) + "ms");
    }
}