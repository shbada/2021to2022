package com.java.effective.item15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayTest3 {
    private static final int[] VALUES = {1, 2, 3, 4};

    /* 배열 복사 (깊은복사 clone) */
    public static final int[] getValues() {
        return VALUES.clone();
    }
}
