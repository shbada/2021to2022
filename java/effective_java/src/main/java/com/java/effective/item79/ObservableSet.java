package com.java.effective.item79;

import com.java.effective.item18.ForwardingSet;

import java.util.*;

public class ObservableSet<E> extends ForwardingSet<E> {
    /**
     * 1 ~ 99 출력
     * @param args
     */
    public static void main(String[] args) {
        ObservableSet<Integer> set = new ObservableSet<>(new HashSet<>());

        set.addObserver((s, e) -> System.out.println(e));

        for (int i = 0; i < 100; i++) {
            set.add(i);
        }
    }

    public ObservableSet(Set<E> set) {
        super(set);
    }

    private final List<SetObserver<E>> observers = new ArrayList<>();

    public void addObserver(SetObserver<E> observer) {
        synchronized(observers) {
            observers.add(observer);
        }
    }

    public boolean removeObserver(SetObserver<E> observer) {
        synchronized(observers) {
            return observers.remove(observer);
        }
    }

    private void notifyElementAdded(E element) {
        synchronized(observers) {
            for (SetObserver<E> observer : observers)
                observer.added(this, element);
        }
    }

    /**
     * 외계인 메서드를 동기화 블록 바깥으로 옮긴다.
     * @param element
     */
    private void notifyElementAdded2(E element) {
        List<SetObserver<E>> snapshot = null;

        synchronized (observers) {
            snapshot = new ArrayList<>(observers);
        }

        for (SetObserver<E> observer : snapshot)
            observer.added(this, element);
    }

    @Override
    public boolean add(E element) {
        boolean added = super.add(element);
        if (added)
            notifyElementAdded(element);
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        boolean result = false;
        for (E element : c)
            result |= add(element); // notifyElementAdded 호출
        return result;
    }

//    private final List<SetObserver<E>> observers = new CopyOnWriteArrayList<>();
//
//    public void addObserver(SetObserver<E> observer) {
//        observers.add(observer);
//    }
//
//    public boolean removeObserver(SetObserver<E> observer) {
//        return observers.remove(observer);
//    }
//
//    private void notifyElementAdded(E element) {
//        for (SetObserver<E> observer : observers)
//            observer.added(this, element);
}
