package seohae.algorithm.C01_알고리즘_구현;

import java.util.stream.IntStream;

public class M10_삽입정렬 {
    public static void main(String[] args) {
        int n = 10;
        int[] arr = {7, 5, 9, 0, 3, 1, 6, 2, 4, 8};

        for (int i = 0; i < n; i++) {
            // 인덱스 i 부터 1까지 감소하며 반복하는 문법
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    // swap
                    int temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                } else {
                    // 자기보다 작은 데이터를 만나면 그 위치에서 멈춤
                    break;
                }
            }
        }

        IntStream.range(0, n).mapToObj(i -> arr[i] + " ").forEach(System.out::println);
    }
}
