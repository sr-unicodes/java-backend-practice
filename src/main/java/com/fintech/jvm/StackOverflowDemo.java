package com.fintech.jvm;

public class StackOverflowDemo {
    private static int callCounter = 0;

    public static void recursiveMethod() {
        callCounter++;
        System.out.println("Stack Frame Number: " + callCounter);
        recursiveMethod();
    }

    public static void main(String[] args) {
        try {
            recursiveMethod();
        } catch (Throwable t) {
            System.err.println("\n--- CRASH DETECTED ---");
            System.err.println("Crashed with exception: " + t);
            System.err.println("Total depth reached before crash: " + callCounter);
        }
    }
}