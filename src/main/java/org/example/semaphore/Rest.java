package org.example.semaphore;

import java.util.concurrent.Semaphore;

public class Rest {

    private final Semaphore semaphore = new Semaphore(5, true);

    public boolean acquire() throws InterruptedException {

        if (this.semaphore.tryAcquire()) {
            System.out.println(Thread.currentThread().getName() + " GOT ONE");
            return true;
        }

        Thread.sleep(5000);
        return acquire();
    }

    public void freeUpPlace() {

        System.out.println("Release");
        this.semaphore.release();
    }

    public int freePlaces() {
        return this.semaphore.availablePermits();
    }
}
