package com.fintech.jvm.oop_account_manager;

public abstract class BankAccount {
    private String accountId ;
    private double balance;

    public BankAccount(String accountId, double balance){
        this.accountId = accountId;
        this.balance = balance;
    }
    public String getAccountId(){
        return accountId;
    }

    public double getBalance(){
        return balance;
    }

    protected void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double amount){
        this.balance = this.balance + amount;
        System.out.println("Deposited: $" + amount);
    }
    public abstract void withdraw(double amount);

}
