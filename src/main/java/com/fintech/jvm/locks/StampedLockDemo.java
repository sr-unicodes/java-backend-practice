package com.fintech.jvm.locks;

import java.util.concurrent.locks.StampedLock;

public class StampedLockDemo {

    public static void main(String[] args) {

        StampedResource sr = new StampedResource();

        Thread r1 = new Thread(() -> sr.read(), "Reader-1");
        Thread r2 = new Thread(() -> sr.read(), "Reader-2");
        Thread r3 = new Thread(() -> sr.read(), "Reader-3");

        Thread w1 = new Thread(() -> sr.write(5), "Writer-1");
        Thread w2 = new Thread(() -> sr.write(7), "Writer-2");
        Thread w3 = new Thread(() -> sr.write(9), "Writer-3");

        r1.start();
        r2.start();
        r3.start();

        w1.start();
        w2.start();
        w3.start();
    }
}

class StampedResource {

    private int value = 0;
    private final StampedLock lock = new StampedLock();


    public int read() {

        long stamp = lock.tryOptimisticRead();
        int currentValue = value;

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
        }

        if (!lock.validate(stamp)) {
            System.out.println(Thread.currentThread().getName() + " optimistic read failed, acquiring read lock...");
            stamp = lock.readLock();
            try {
                currentValue = value;
            } finally {
                lock.unlockRead(stamp);
            }
        }
        System.out.println(Thread.currentThread().getName() + " reads value as " + currentValue);
        return currentValue;
    }

    public void write(int newValue) {
        long stamp = lock.writeLock();
        try {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            value = newValue;
            System.out.println(Thread.currentThread().getName() + " changes value to " + value);
        } finally {
            lock.unlockWrite(stamp);
        }
    }
}


