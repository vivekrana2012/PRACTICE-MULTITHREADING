package org.example;

public class SimpleDeadLock {

    private static void method1(String str1, String str2) throws InterruptedException {

        synchronized (str1) {
            Thread.sleep(1000);
            method2(str2, str1);
        }
    }

    private static void method2(String str1, String str2) throws InterruptedException {

        synchronized (str1) {
            Thread.sleep(1000);
            method1(str2, str1);
        }
    }

    public static void main(String[] args) throws InterruptedException {

        String lock1 = "Vivek";
        String lock2 = "Devendra";

        method1(lock1, lock2);
        method2(lock2, lock1);
    }
}
