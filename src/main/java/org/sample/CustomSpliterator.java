package org.sample;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;

public class CustomSpliterator implements Spliterator<Integer> {
    private int last = 1;

    @Override
    public boolean tryAdvance(Consumer<? super Integer> action) {
        if (action == null)
            throw new NullPointerException();

        while (!isPrime(last)) {
            last++;
        }
        action.accept(last);
        last++;
        return true;
    }

    private boolean isPrime(int elem) {
        return !IntStream.rangeClosed(2, elem / 2).anyMatch(elem1 -> elem % elem1 == 0);
    }

    @Override
    public Spliterator<Integer> trySplit() {
        return null;
    }

    @Override
    public long estimateSize() {
        return 0;
    }

    @Override
    public int characteristics() {
        return 0;
    }

    public static void main(String[] args) {
        StreamSupport.stream(CustomSpliterator::new,0,false).limit(10).forEach(System.out::println);
    }
}
