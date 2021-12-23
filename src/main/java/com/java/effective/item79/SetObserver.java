package com.java.effective.item79;

@FunctionalInterface
public interface SetObserver<E> {
    // ObservableSet 에 원소가 더해지면 호출된다.
    void added(ObservableSet<E> set, E element);
}
