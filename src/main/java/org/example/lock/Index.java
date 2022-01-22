package org.example.lock;

public class Index {

    public static void main(String[] args) throws InterruptedException {

        State state = new State();

        Thread t1 = new Thread(state::incrementLocked);
        Thread t2 = new Thread(state::incrementLocked);
        Thread t3 = new Thread(state::incrementLocked);

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println("Counter -> " + state.getCounter());
    }
}
