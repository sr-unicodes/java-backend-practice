package com.fintech.jvm.message_broker;

import java.util.LinkedList;
import java.util.Queue;
public class TransactionBroker {

    private Queue<String> queue = new LinkedList<>();
    private final int MAX_CAPACITY = 3;

    public synchronized void addTransaction(String tx){
        while (queue.size() == MAX_CAPACITY){
            System.out.println("Queue full. Producer waiting...");

            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        queue.add(tx);
        System.out.println("Producer added: " + tx);
        notifyAll();
    }

    public synchronized void processTransaction(){
        while (queue.isEmpty()){
            System.out.println("Queue empty. Consumer waiting...");
            try {
                wait();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        String tx = queue.poll();
        System.out.println("Consumer processed: " + tx);
        notifyAll();
    }
}
