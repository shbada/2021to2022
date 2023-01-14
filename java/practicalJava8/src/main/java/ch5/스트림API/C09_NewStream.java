package ch5.스트림API;

import dto.SampleDto;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class C09_NewStream {
    public static void main(String[] args) {
        /**
         * 데이터 평면화
         * 상당히 많은 데이터가 배열 내부에 또 배열이 있거나, 맵 데이터에 값이 배열일 수 있다.
         * 이렇게 다차원 데이터가 있는 환경에서 필요한 기능 중 하나가 데이터 평면화이다.
         */

        String[][] rawData = new String[][] {
                {"a", "b"}, {"c", "d"}, {"e", "a"}, {"a", "h"}
        };

        List<String[]> rawList = Arrays.asList(rawData);

        // 2차원 배열의 데이터 중 "a"를 찾기 위한 코드
        // 배열의 크기를 직접 알고있어야 사용할 수 있는 코드이다.
        rawList.stream()
                .filter(array -> "a".equals(array[0].toString()) || "a".equals(array[1].toString()))
                .forEach(array -> System.out.println(array[0] + "," + array[1]));

        /**
         * flatMap : 위 코드를 flatMap 을 사용해서 데이터 평면화한다.
         * flatMap 은 스트림 내부에 있는 리스트 혹은 배열을 1차원 데이터를 변환해준다.
         */

        rawList.stream()
                /* 배열을 펼친다. 라는 의미로 이해하자. */
                .flatMap(array -> Arrays.stream(array))
                .filter(data -> "a".equals(data.toString()))
                .forEach(data -> System.out.println(data));
    }
}
