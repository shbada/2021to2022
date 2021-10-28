package com.java.effective.item28;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ChooserGeneric<T> {
    private final T[] choiceArray;

    public ChooserGeneric(Collection<T> choices) {
        // 경고 발생 : T가 무슨 타입인지 알 수 없으니 형변환이 런타임에도 안전한지 알 수 없다
        this.choiceArray = (T[]) choices.toArray();
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        // Object type - 각 클라이언트에서 형변환 필요
        return choiceArray[rnd.nextInt(choiceArray.length)];
    }
}
