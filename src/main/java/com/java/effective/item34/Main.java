package com.java.effective.item34;

public class Main {
    public static final int APPLE_FUJI = 0;
    public static final int APPLE_PIPPIN = 1;
    public static final int APPLE_GRANNY_SMITH = 2;

    public static final int ORANGE_NAVEL = 0;
    public static final int ORANGE_TEMPLE = 1;
    public static final int ORANGE_BLOOD = 2;

    public static void main(String[] args) {
        for (Planet p : Planet.values()) {
            System.out.println(p);
        }

        System.out.println(Operation.valueOf("PLUS").equals(Operation.PLUS));
    }
}
