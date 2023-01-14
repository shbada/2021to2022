package com.java.effective.item18;

import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;

/**
 * ForwardingSet 상속으로 변경
 * @param <E>
 */
public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount = 0;

    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount = c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}
