package me.einfachbeez.utility.jda.util;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * @author EinfachBeez | https://github.com/EinfachBeez
 */
@FunctionalInterface
public interface TriConsumer<A, B, T> {

    void accept(@NotNull A a, @NotNull B b, @NotNull T t);

    default TriConsumer<A, B, T> andThen(@NotNull TriConsumer<? super A, ? super B, ? super T> after) {
        Objects.requireNonNull(after);

        return ((a, b, t) -> {
            accept(a, b, t);
            after.accept(a, b, t);;
        });
    }

}
