package com.java.effective.item28;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chooser {
    private final Object[] choiceArray;

    public Chooser(Collection choices) {
        this.choiceArray = choices.toArray();
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        // Object type - 각 클라이언트에서 형변환 필요
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }
}
