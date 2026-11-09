package com.fintech.jvm.oop_account_manager;

public class AccountEngine {
    public static void main(String[] args) {
        BankAccount mySavings = new SavingsAccount("SAV-101", 500.00);
        BankAccount myBusiness = new CurrentAccount("BIZ-202", 100.00, 1000.00);

        System.out.println("--- Testing Savings Account ---");
        mySavings.deposit(50.00);
        mySavings.withdraw(400.00);
        mySavings.withdraw(100.00);

        System.out.println("\n--- Testing Business Current Account ---");
        myBusiness.withdraw(500.00);
        myBusiness.withdraw(800.00);

    }
    }


