package com.fintech.jvm;

// 1. The Shared Resource (The Waiting Room)
class Bakery {
    private int bread = 0; // The shelf can hold max 1 loaf

    public synchronized void bake() {
        try {
            // If the shelf is full, the Baker goes to sleep
            while (bread == 1) {
                System.out.println("Shelf is full. Baker is waiting...");
                wait();

            }

            // The Baker wakes up (or the shelf was empty), so they bake!
            bread = 1;
            System.out.println("👨‍🍳 Baker baked a loaf of bread!");
            notify();


        } catch (InterruptedException e) { }
    }

    public synchronized void buy() {
        try {
            // If the shelf is empty, the Customer goes to sleep
            while (bread == 0) {
                System.out.println("Shelf is empty. Customer is waiting...");
                wait();

            }

            // The Customer wakes up (or the shelf had bread), so they buy!
            bread = 0;
            System.out.println("🛒 Customer bought the bread!");
            notify();


        } catch (InterruptedException e) { }
    }
}

// 2. The Main Program
public class BakeryFile{
    public static void main(String[] args) {
        Bakery bakery = new Bakery();

        // The Baker Thread (Runs 5 times)
        Thread bakerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                bakery.bake();
            }
        });

        // The Customer Thread (Runs 5 times)
        Thread customerThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                bakery.buy();
            }
        });

        // Start the simulation!
        bakerThread.start();
        customerThread.start();
    }
}
