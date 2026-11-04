package com.fintech.jvm;
class UPIPayment implements Runnable {

    @Override
    public void run() {
        System.out.println("Payment processed!");
    }
}
public class UpiPaymentDemo {
    public static void main(String[] args) {


        System.out.println("Main program started.");

        UPIPayment myJob = new UPIPayment();

        Thread worker1 = new Thread(myJob);

        worker1.start();

        System.out.println("Main program finished.");
    }
}