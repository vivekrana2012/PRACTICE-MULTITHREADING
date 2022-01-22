package org.example.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class State {

    private int counter;
    private final Lock lock = new ReentrantLock();

    public void increment() {
        IntStream.range(0, 10).forEach(i -> counter++);
    }

    public void incrementLocked() {

        lock.lock();

        try {
            IntStream.range(0, 10).forEach(i -> counter++);
        } finally {
            lock.unlock();
        }
    }

    public int getCounter() {
        return this.counter;
    }
}
