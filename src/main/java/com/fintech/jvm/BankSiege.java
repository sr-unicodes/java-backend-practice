package com.fintech.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BankSiege {
    public static void main(String[] args) {

        System.out.println("Starting the siege...");
        long startTime = System.currentTimeMillis();

        ExecutorService pool = Executors.newFixedThreadPool(10);

        List<Future<Integer>> receipts = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            Future<Integer> receipt = pool.submit(() -> {
                return 1;
            });
            receipts.add(receipt);
        }

        int totalBalance = 0;

        for (Future<Integer> receipt : receipts){
            try {
                totalBalance += receipt.get();
            }catch (InterruptedException | ExecutionException e){
                e.printStackTrace();
            }
        }

            pool.shutdown();

        long endTime = System.currentTimeMillis();
        System.out.println("Final Balance: $" + totalBalance);
        System.out.println("Processed in " + (endTime - startTime) + " milliseconds.");
    }
}
