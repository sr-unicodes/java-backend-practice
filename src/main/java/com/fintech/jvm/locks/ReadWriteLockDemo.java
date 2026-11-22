package com.fintech.jvm.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockDemo {

    public static void main(String[] args) {

        SharedResources sr = new SharedResources();

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

class SharedResources {

    private int value = 0;
    ReadWriteLock rwLock = new ReentrantReadWriteLock();

    Lock rl = rwLock.readLock();
    Lock wl = rwLock.writeLock();

    public int read() {
        rl.lock();
        try {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            System.out.println(Thread.currentThread().getName() + " read value as " + value);
            return value;

        } finally {
            rl.unlock();
        }
    }

    public void write(int newValue) {
        wl.lock();
        try {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            value = newValue;
            System.out.println(Thread.currentThread().getName() + " changes value to " + value);
        } finally {
            wl.unlock();
        }
    }
}
