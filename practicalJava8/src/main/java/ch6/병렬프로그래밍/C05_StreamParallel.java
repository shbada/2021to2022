package ch6.병렬프로그래밍;

import dto.SampleDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.TimeUnit;

public class C05_StreamParallel {
    /**
     * 스트림 API 에서는 parallelStream 메서드를 이용해서 스트림 객체를 생성하는 것만으로 병렬 처리가 된다.
     * 기존의 코드를 크게 고치지 않아도 병렬 처리로 변경 가능하다.
     */

    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(4, 2, 8, 1, 3, 5);

        // 최대값 구하기 - 병렬
        int max = intList.parallelStream().reduce(1, Integer::max);
        System.out.println(max);

        // 최소값 구하기 - 병렬
        int min = intList.parallelStream().reduce(1, Integer::min);
        System.out.println(min);

        /* 스트림의 병렬 처리를 위해 내부적으로 어떻게 스레드를 생성하고 사용되는지 알아보자. */
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        intList.parallelStream().forEach(value -> {
            // 현재 스레드 이름을 구한다.
            String threadName = Thread.currentThread().getName();

            // 스레드 이름과 데이터 값을 출력한다.
            LocalDateTime currentTime = LocalDateTime.now();

            /**
             * 데이터의 순서는 섞여서 출력 될 것이다. (병렬이므로)
             * 결과는 항상 다르게 나온다.
             * 순서가 중요하다면 병렬 처리를 해서는 안된다.
             * PC 나 서버의 코어 수에 따라 스레드가 생성된다.
             * 코어 수가 4개라서 스레드가 4개가 동시에 생성되는데, 1개의 스레드 이름은 main 이고 3개는 ForkJoinPool 로 생성된다.
             * 코어 수 기반의 스레드 생성은 스트림에서 제어한 것이 아니라 컨커런트 API 의 ForkJoinPool 의 기본 값이며, 여기에 영향을 받은 것이다.
             */
            System.out.printf(currentTime.format(formatter) + "-> Thread Name : %s, Stream Value : %s" +
                    "\n", threadName, value);

            // 시간 확인을 위해 2초간 sleep 한다.
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {}
        });

        /**
         * parallel : 순차 처리 스트림을 병렬 처리로 변경한다.
         * sequential : 병렬 처리 스트림을 순차 처리로 변경한다.
         */

        // limit 처리 이후 순차로 변경한다.
        // list.stream().limit(100).parallel().reduce(Integer::sum);

        /**
         * 분할반복 Spliterator
         * = Spliterator 인터페이스의 제공 - 분할 가능한 반복문
         * = 내부적으로 스트림을 사용한다.
         */
        List<SampleDto> sampleDtoList = new ArrayList<>();

        // spliterator 객체 생성
        Spliterator<SampleDto> spliterator = sampleDtoList.spliterator();

        // 순차 처리
        // Iterator 의 hasNext 또는 next 메서드 호출, while, for 없이도 동일한 효과를 얻게된다.
        // Spliterator 내부에서 사전에 정의된 방식으로 처리하여 개발해야할 코드량을 줄이고 실수 영역이 줄어든다.
        spliterator.forEachRemaining((sampleDto -> {
            System.out.println(sampleDto.getIdx());
        }));
    }
}
