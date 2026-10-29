package com.fintech.jvm;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Transaction {
    String userId;
    double amount;

    public Transaction(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }
}

public class FinTechPipeline {
    public static void main(String[] args) {

        List<Transaction> transactions = Arrays.asList(
                new Transaction("User_A", 100.0),
                new Transaction("User_B", 50.0),
                new Transaction("User_A", -20.0),
                new Transaction("User_B", 200.0),
                new Transaction("User_C", 1000.0)
        );


        Map<String, Double> userBalances = transactions.stream()
                .collect(
                        Collectors.groupingBy(
                                transaction -> transaction.userId,

                                Collectors.summingDouble(transaction -> transaction.amount)
                        )
                );


        System.out.println(userBalances);

    }
}
