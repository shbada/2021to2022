package com.java.effective.item35;

public enum Ensemble {
    SOLO, DUET, TRIO, QUARTET;

    public int numberOfMusicians() {
        return ordinal() + 1;
    }
}
