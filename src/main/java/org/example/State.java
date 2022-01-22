package org.example;

public class State {

    private String name;

    public void produce(String name) {

        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + ", Producing...");
            this.name = name;

            notify();
        }
    }

    public void consume() {

        synchronized (this) {
            System.out.println("Consuming...");

            if (this.name == null) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }

            System.out.println(Thread.currentThread().getName() + "- Consumed - " + this.name);
            this.name = null;
        }
    }
}
