package com.java.effective.item15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayTest2 {
    private static final Integer[] VALUES = {1, 2, 3, 4};

    /* 수정 불가능한 리스트 */
    public static final List<Integer> VALUE_LIST
            = Collections.unmodifiableList(Arrays.asList(VALUES));
}
