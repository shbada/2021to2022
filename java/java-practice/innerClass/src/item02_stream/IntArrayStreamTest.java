package item02_stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class IntArrayStreamTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};

        for (int num : arr) {
            System.out.println(num);
        }

        System.out.println("--------");

        /* stream */
        int sum = Arrays.stream(arr).sum();
        System.out.println("sum: " + sum);

        /* Integer 을 핸들링할 수 있는 IntStream 을 반환해준다 */
        IntStream stream = Arrays.stream(arr); // stream 메서드
        stream.forEach(System.out::println);

        System.out.println("--------");
        // stream.forEach(System.out::println); (에러 발생 - 스트림 재사용 불가)

        /* arr 요소 1개씩 을 출력하라는 의미 */
        Arrays.stream(arr).forEach(System.out::println);
    }
}
