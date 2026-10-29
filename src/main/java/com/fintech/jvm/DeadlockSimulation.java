package com.fintech.jvm;

class Account {
    int id;
    int balance;

    public Account(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }
}

class Bank {
    public void transfer(Account from, Account to, int amount) {
        Account firstLock;
        Account secondLock;

        if (from.id < to.id ){
            firstLock = from;
            secondLock = to;
        }else{
            firstLock = to;
            secondLock = from;
        }

        synchronized (firstLock) {
            System.out.println(Thread.currentThread().getName() + " LOCKED Account " + firstLock.id);

            try { Thread.sleep(50); } catch (InterruptedException e) {}

            System.out.println(Thread.currentThread().getName() + " waiting to lock Account " + secondLock.id + "...");

            synchronized (secondLock) {
                from.balance -= amount;
                to.balance += amount;
                System.out.println(Thread.currentThread().getName() + " SUCCESS! Transfer complete.");
            }
        }
    }
}

public class DeadlockSimulation {
    public static void main(String[] args) {
        Account alice = new Account(1, 1000);
        Account bob = new Account(2, 1000);
        Bank bank = new Bank();

        Thread t1 = new Thread(() -> bank.transfer(alice, bob, 100), "Thread-AliceToBob");

        Thread t2 = new Thread(() -> bank.transfer(bob, alice, 100), "Thread-BobToAlice");

        t1.start();
        t2.start();
    }
}
