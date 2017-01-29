package org.sample;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.LongStream;
import java.util.stream.StreamSupport;

public class CustomSpliterator implements Spliterator<Long> {
    private long last;
    private Long start = 1L;
    private Long end = null;

    public CustomSpliterator(Long start, Long end) {
        this.start = start;
        this.end = end;
        this.last = start;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Long> action) {
        if (action == null)
            throw new NullPointerException();

        while (!isPrime(last)) {
            last++;
        }
        action.accept(last);
        last++;
        return true;
    }

    private boolean isPrime(long elem) {
        return !LongStream.rangeClosed(2, elem / 2).anyMatch(elem1 -> elem % elem1 == 0);
    }

    @Override
    public Spliterator<Long> trySplit() {
        Spliterator<Long> result = new CustomSpliterator(this.start, this.end == null?1000:(end-start)/2+start);
        return result;
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
        StreamSupport.stream(()->new CustomSpliterator(1L, null),0,false).parallel().limit(1000).forEach(System.out::println);
    }
}
