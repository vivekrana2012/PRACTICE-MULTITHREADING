package org.example.cyclic_barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Index {

    private static final CyclicBarrier barrier = new CyclicBarrier(3, () -> System.out.println("All Done"));

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        IntStream.range(0, 3).forEach(i -> {
            executorService.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " - Done");
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException ex) {
                    System.out.println("Barrier Broken!");
                }
            });
        });

        IntStream.range(0, 3).forEach(i -> {
            executorService.submit(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " - Done");
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException ex) {
                    System.out.println("Barrier Broken!");
                }
            });
        });

        executorService.shutdown();
    }
}
