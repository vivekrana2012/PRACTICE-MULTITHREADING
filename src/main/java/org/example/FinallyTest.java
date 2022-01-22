package org.example;

public class FinallyTest {

    private static void finallyTest(int i) {

        try {

            if (i == 0) throw new RuntimeException("Finally Test");
            else System.out.println("Success");
        } finally {
            System.out.println("In Finally");
        }

        System.out.println("End of Method");
    }

    public static void main(String[] args) {

        finallyTest(1);
        finallyTest(0);
    }
}
