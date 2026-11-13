package com.fintech.jvm.concurrency;

public class AtmThread implements Runnable{
    private JointBankAccount account;
    private String userName;

    public AtmThread(JointBankAccount account, String userName) {
        this.account = account;
        this.userName = userName;
    }
    @Override
    public void run(){
        account.withdraw(1000.00, userName);
    }
    public static void mailn(String[] args){
        JointBankAccount sharedAccount = new JointBankAccount();

        AtmThread user1 = new AtmThread(sharedAccount, "Alice");
        AtmThread user2 = new AtmThread(sharedAccount, "Bob");


        Thread t1 = new Thread(user1);
        Thread t2 = new Thread(user2);

        t1.start();
        t2.start();
    }
}
