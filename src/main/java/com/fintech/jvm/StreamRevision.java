package com.fintech.jvm;

import java.util.*;

public class StreamRevision {
    public static void main(String[] args) {
        List<Integer> transactions = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 1_000_000; i++) {
            transactions.add(rand.nextInt(5000));
        }

        // 2. Sequential Stream
        long startSeq = System.currentTimeMillis();

        long countSeq = transactions.stream()
                .filter(amount -> amount > 1000)
                .count();

        long endSeq = System.currentTimeMillis();
        System.out.println("Sequential Time: " + (endSeq - startSeq) + "ms (Count: " + countSeq + ")");

        // 3. Parallel Stream
        long startPar = System.currentTimeMillis();

        long countPar = transactions.parallelStream()
                .filter(amount -> amount > 1000)
                .count();

        long endPar = System.currentTimeMillis();
        System.out.println("Parallel Time: " + (endPar - startPar) + "ms (Count: " + countPar + ")");
    }
}
