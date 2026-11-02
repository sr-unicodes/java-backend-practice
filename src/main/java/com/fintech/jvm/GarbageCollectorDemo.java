package com.fintech.jvm;

public class GarbageCollectorDemo {

    public static void main(String[] args) throws InterruptedException {


        System.out.println("=== STARTING OBJECT ALLOCATION CYCLE ===");

        for (int i = 1; i <= 30; i++) {
            byte[] shortLivedGarbage = new byte[10 * 1024 * 1024];

            System.out.println("Iteration " + i + ": Allocated 10 MB");
            Thread.sleep(100);
        }

        System.out.println("=== ALLOCATION CYCLE COMPLETE ===");
    }
}