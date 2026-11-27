package com.fintech.jvm.locks;

import java.util.concurrent.atomic.AtomicInteger;

public class CasDemo {
    public static void main(String[] args) {
        LikeCounter likeCounter = new LikeCounter();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> likeCounter.like(), "User-" + (i + 1));
            threads[i].start();
        }

        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("Total likes: " + likeCounter.getTotalLikes());
    }
}

class LikeCounter {
    private final AtomicInteger totalCount = new AtomicInteger(0);

    public void like() {
        while (true) {
            int currentCount = totalCount.get();
            int nextCount = currentCount + 1;

            if (totalCount.compareAndSet(currentCount, nextCount)) {
                return;
            }
            System.out.println(Thread.currentThread().getName() + " detected conflict, retrying...");
        }
    }

    public int getTotalLikes() {
        return totalCount.get();
    }
}