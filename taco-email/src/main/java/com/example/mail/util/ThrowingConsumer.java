package com.example.mail.util;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowingConsumer<T, E extends Throwable> {
    void accept(T t) throws E;

    static <T, E extends Throwable>Consumer<T> uncheckedConsumer(ThrowingConsumer<T, E> tc) {
        return i -> {
            try {
                tc.accept(i);
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        };
    }
}
