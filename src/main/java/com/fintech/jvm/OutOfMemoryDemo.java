package com.fintech.jvm;

import java.util.ArrayList;
import java.util.List;

public class OutOfMemoryDemo {

    public static void main(String[] args) {
        List<byte[]> memoryLeakingList = new ArrayList<>();
        int count = 0;

        System.out.println("Starting object allocation in Heap...");

        try {
            while (true) {
                memoryLeakingList.add(new byte[1024 * 1024]);
                count++;
                System.out.println("Allocated " + count + " MB to Heap");
                Thread.sleep(50);
            }
        } catch (OutOfMemoryError e) {
            System.err.println("\n--- HEAP EXHAUSTED ---");
            System.err.println("Total memory allocated before crash: " + count + " MB");
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}