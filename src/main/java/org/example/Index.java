package org.example;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class Index {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<String> task1 = new FutureTask<>(() -> System.out.println(Thread.currentThread().getName()), "");
        FutureTask<String> task2 = new FutureTask<>(() -> Thread.currentThread().getName());

        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(task1.get());
        System.out.println(task2.get());

        State state = new State();

        Thread t3 = new Thread(state::consume);
        Thread t5 = new Thread(state::consume);

        Thread t4 = new Thread(() -> state.produce("Vivek"));
        Thread t6 = new Thread(() -> state.produce("Devendra"));

        t3.start();
        t4.start();
        t5.start();
        t6.start();
    }
}
