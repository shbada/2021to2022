package com.java.effective.item35;

public enum Ensemble2 {
    SOLO(1),
    DUET(2),
    TRIO(3),
    QUARTET(4);

    private final int numberOfMusicians;
    Ensemble2(int size) {
        this.numberOfMusicians = size;
    }

    public int numberOfMusicians() {
        return numberOfMusicians;
    }
}
