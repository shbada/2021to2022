package com.algorithm._00_빠른문법;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _06_리스트_정렬 {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 5, 3, 6, 2);

        /** 정렬 */
        Collections.sort(list); /* 정렬 */

        /** 역순 정렬 */
        List<Integer> sumList = List.of(1, 5, 3, 6, 2);
        Collections.sort(sumList, Collections.reverseOrder());
        sumList.sort(Collections.reverseOrder());

        Collections.sort(sumList);
        Collections.reverse(sumList);
    }
}
