package com.fintech.jvm;

public class StringPoolDemo {

    public static void main(String[] args) {
        String s1 = "fintech";
        String s2 = "fintech";

        String s3 = new String("fintech");

        String s4 = s3.intern();

        System.out.println("--- REFERENCE EQUALITY COMPARISONS (==) ---");
        System.out.println("s1 == s2 (Both literals in SCP): " + (s1 == s2));
        System.out.println("s1 == s3 (Literal vs Heap object): " + (s1 == s3));
        System.out.println("s1 == s4 (Literal vs Interned string): " + (s1 == s4));

        System.out.println("\n--- MEMORY ADDRESS IDENTITY HASHES ---");

        System.out.println("s1 Memory Reference Hash: " + System.identityHashCode(s1));
        System.out.println("s2 Memory Reference Hash: " + System.identityHashCode(s2));
        System.out.println("s3 Memory Reference Hash: " + System.identityHashCode(s3));
        System.out.println("s4 Memory Reference Hash: " + System.identityHashCode(s4));
    }
}