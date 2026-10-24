package com.fintech.jvm;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class BankAccount1 {

    // 1. Declare an AtomicInteger named 'balance' and start it at 1000.
    AtomicInteger balance = new AtomicInteger(1000);

    public void deductOneDollar() {

        balance.decrementAndGet();
        // 2. Use the built-in AtomicInteger method to safely subtract 1.
        // No synchronized keyword!
    }

    public int getBalance() {
        // 3. Return the current value of the AtomicInteger.
        return balance.get(); // change this
    }
}

public class BankHeist {
    public static void main(String[] args) throws InterruptedException {
        BankAccount1 account = new BankAccount1();

        // Spin up a thread pool with 100 workers
        ExecutorService executor = Executors.newFixedThreadPool(100);

        System.out.println("Starting balance: " + account.getBalance());
        System.out.println("Unleashing 1000 threads...");

        // Fire 1000 tasks into the thread pool
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                account.deductOneDollar();
            });
        }

        // Shut down the pool and wait for all threads to finish
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("Final balance: " + account.getBalance());
        if(account.getBalance() == 0) {
            System.out.println("SUCCESS! CAS protected the bank lock-free!");
        } else {
            System.out.println("FAILED! Race condition detected.");
        }
    }
}