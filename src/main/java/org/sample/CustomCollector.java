package org.sample;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.Collections.emptySet;

public class CustomCollector<T> implements Collector<T, List<T>, String> {
    private static final Gson GSON = new Gson();

    @Override
    public Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    @Override
    public BiConsumer<List<T>, T> accumulator() {
        return List::add;
    }

    @Override
    public BinaryOperator<List<T>> combiner() {
        return (left, right) -> { left.addAll(right); return left; };
    }

    @Override
    public Function<List<T>, String> finisher() {
        return list -> GSON.toJson(list, List.class);
    }

    @Override
    public Set<Characteristics> characteristics() {
        return emptySet();
    }
}
