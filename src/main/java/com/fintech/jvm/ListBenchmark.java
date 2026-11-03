package com.fintech.jvm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListBenchmark {

    public static void main(String[] args) {
        int dataSize = 500_000;

        List<Integer> arrayList = new ArrayList<>();
        List<Integer> linkedList = new LinkedList<>();

        System.out.println("Populating lists with " + dataSize + " elements. Please wait...");
        for (int i = 0; i < dataSize; i++) {
            arrayList.add(i);
            linkedList.add(i);
        }
        System.out.println("Populating complete.\n");

        System.out.println("--- TEST 1: Insert at Index 0 ---");

        long startALFront = System.nanoTime();
        arrayList.add(0, 999);
        long endALFront = System.nanoTime();
        System.out.println("ArrayList Insert at Front:  " + (endALFront - startALFront) + " ns");

        long startLLFront = System.nanoTime();
        linkedList.add(0, 999);
        long endLLFront = System.nanoTime();
        System.out.println("LinkedList Insert at Front: " + (endLLFront - startLLFront) + " ns");


        System.out.println("\n--- TEST 2: Insert at Middle (Index 250,000) ---");
        int middleIndex = dataSize / 2;

        long startALMid = System.nanoTime();
        arrayList.add(middleIndex, 999);
        long endALMid = System.nanoTime();
        System.out.println("ArrayList Insert at Middle:  " + (endALMid - startALMid) + " ns");

        long startLLMid = System.nanoTime();
        linkedList.add(middleIndex, 999);
        long endLLMid = System.nanoTime();
        System.out.println("LinkedList Insert at Middle: " + (endLLMid - startLLMid) + " ns");
    }
}