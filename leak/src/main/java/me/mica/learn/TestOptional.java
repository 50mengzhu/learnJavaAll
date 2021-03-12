package me.mica.learn;

import java.util.Optional;

public class TestOptional {

    public static void main(String[] args) {
        String nn = null;
        Optional<String> s = Optional.ofNullable(nn);
        s.ifPresent(System.out::println);

        String notNull = "liusw";
        Optional<String> notS = Optional.ofNullable(notNull);
        notS.ifPresent(System.out::println);
    }
}
