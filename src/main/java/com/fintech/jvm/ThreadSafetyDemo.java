package com.fintech.jvm;

class BankAccount {
    private int balance = 1000;

    public synchronized void withdraw(String user, int amount) {
        System.out.println(user + " is checking the balance...");

        if (balance >= amount) {
            System.out.println(user + " sees enough money (" + balance + "). Proceeding to withdraw...");

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // THE ACT
            balance = balance - amount;
            System.out.println(user + " successfully withdrew! New Balance: " + balance);

        } else {
            System.out.println(user + " failed to withdraw. Insufficient funds.");
        }
    }
}

class ATMJob implements Runnable {
    private BankAccount account;
    private String userName;

    public ATMJob(BankAccount account, String userName) {
        this.account = account;
        this.userName = userName;
    }

    @Override
    public void run() {
        account.withdraw(userName, 1000);
    }
}

public class ThreadSafetyDemo {
    public static void main(String[] args) {
        BankAccount jointAccount = new BankAccount();


        Thread Alice = new Thread(new ATMJob(jointAccount, "Alice"));
        Thread Bob = new Thread(new ATMJob(jointAccount, "Bob"));

        Alice.start();
        Bob.start();
    }
}