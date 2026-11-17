package com.fintech.jvm.concurrency;


public class JointBankAccount {

    private double balance = 1000.00;
    public synchronized void withdraw(double amount, String user){
        if (balance >= amount){
            try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= amount;
            System.out.println(user + " successfully withdrew $" + amount + ". \nRemaining balance: $" + balance);
        }else{
            System.out.println(user + " declined. \nInsufficient funds.");
        }
    }
}
