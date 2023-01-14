package _2_코틀린에서_null을_다루는_방법;

import org.jetbrains.annotations.NotNull;

public class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

//    @Nullable // null 이 될 수 있다.
    @NotNull // null 이 될 수 없다.
    public String getName() {
        return name;
    }
}
