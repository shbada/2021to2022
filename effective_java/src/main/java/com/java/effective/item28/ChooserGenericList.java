package com.java.effective.item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ChooserGenericList<T> {
    private final List<T> choiceList;

    public ChooserGenericList(Collection<T> choices) {
        // 경고 발생 : T가 무슨 타입인지 알 수 없으니 형변환이 런타임에도 안전한지 알 수 없다
        this.choiceList = new ArrayList<>(choices);
    }

    public Object choose() {
        Random rnd = ThreadLocalRandom.current();
        return choiceList.get(rnd.nextInt(choiceList.size()));
    }
}
