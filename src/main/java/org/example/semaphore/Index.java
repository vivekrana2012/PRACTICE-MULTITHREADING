package org.example.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Index {

    public static void main(String[] args) throws InterruptedException {

        Rest rest = new Rest();

        ExecutorService executorService = Executors.newFixedThreadPool(7);

        IntStream.range(0, 7).forEach(i -> executorService.submit(rest::acquire));

        Thread.sleep(5000);

        IntStream.range(0, 4).forEach(i -> executorService.submit(rest::freeUpPlace));

        Thread.sleep(5000);

        System.out.println("Free - " + rest.freePlaces());

        executorService.shutdown();
    }
}
