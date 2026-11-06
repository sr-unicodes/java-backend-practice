package com.fintech.jvm;

class NumberPrinter {
    private int count = 1;
    public synchronized void printOdd(){
        try{
            while(count % 2 == 0){
                wait();
            }

            System.out.println("odd thread:" + count);
            count++;
            notify();
        }catch (InterruptedException e){}
    }

    public synchronized void printEven(){
        try{
            while(count % 2 != 0 ){
                wait();
            }
            System.out.println("Even thread: "+ count);
            count++;
            notify();

        }catch (InterruptedException e){}
    }
}
public class OddEvenThreadDemo {
    public static void main(String[] args){
        NumberPrinter numberPrinter = new NumberPrinter();

        Thread oddThread = new Thread(() ->{
            for (int i = 0; i < 5; i++) {
                numberPrinter.printOdd();
            }
        });
        Thread evenThread = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                numberPrinter.printEven();
            }
        });
        oddThread.start();
        evenThread.start();

    }

}
