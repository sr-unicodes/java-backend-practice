package com.fintech.jvm.message_broker;

public class MessageBrokerApp {
    public static void main(String[] args){
        TransactionBroker broker = new TransactionBroker();

        Thread producer = new Thread(()-> {
            for (int i = 1; i <= 6; i++) {
                broker.addTransaction("TXN-" + i);
                try {
                    Thread.sleep(100);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(() ->{
            for (int i = 1; i <= 6; i++) {
                broker.processTransaction();
                try {
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        producer.start();
        consumer.start();

    }

}
