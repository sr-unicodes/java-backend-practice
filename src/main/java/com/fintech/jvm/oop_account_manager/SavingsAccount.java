package com.fintech.jvm.oop_account_manager;

public class SavingsAccount extends BankAccount{
    public SavingsAccount(String accountId, double balance) {
        super(accountId, balance);
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() - amount >= 100){
            setBalance(getBalance() - amount);
            System.out.println("Withdrawal successful! New balance: $" + getBalance());
        } else {
            System.out.println("Declined: Must maintain $100 minimum balance.");
        }
        }
    }



