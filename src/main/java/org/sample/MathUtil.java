package org.sample;

public class MathUtil {

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println("fib("+i+"): " + fibonacci(i));
        }
    }

    static long fibonacci(long num) {
        if (num < 2) return 1;
        return fibonacci(num-2) + fibonacci(num-1);
    }
}
