package com.fintech.jvm;

import java.util.Optional;

public class Lambda_Practice {
    @FunctionalInterface
    interface StringProcessor{
        String lambda(String s);
    }

public static void main(String[] args){
        StringProcessor toUpper = (str) -> str.toUpperCase();

        StringProcessor reverser = (str) -> {
            return new StringBuilder(str).reverse().toString();
        };
            System.out.println("Uppercase: " + toUpper.lambda("engineer"));
            System.out.println("Reversed: " + reverser.lambda("fintech"));

}
}
