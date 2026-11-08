package com.fintech.jvm;

import java.util.ArrayList;
import java.util.List;

public class SeniorPaymentProcessor {

       public enum TransactionStatus {
        PENDING("Transaction is being processed"),
        SUCCESS("Money transferred successfully! "),
        FAILED("Insufficient funds.");

        private final String displayMessage;

        TransactionStatus(String displayMessage) {
            this.displayMessage = displayMessage;
        }

        public String getDisplayMessage() {
            return displayMessage;
        }
    }


    static class Transaction {
        String id;
        TransactionStatus status;

        public Transaction(String id, TransactionStatus status) {
            this.id = id;
            this.status = status;
        }
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("TXN-001", TransactionStatus.PENDING));
        transactions.add(new Transaction("TXN-002", TransactionStatus.SUCCESS));
        transactions.add(new Transaction("TXN-003", TransactionStatus.FAILED));

        for (Transaction txn : transactions){
            System.out.println(txn.id + "-" + txn.status.getDisplayMessage());
        }
    }



}





