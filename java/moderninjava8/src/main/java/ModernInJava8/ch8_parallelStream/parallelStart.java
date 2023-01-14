package ModernInJava8.ch8_parallelStream;

import java.util.stream.Stream;

public class parallelStart {
    public static void main(String[] args) {

    }

    /**
     * 아래 Long::sum 로직은 n이 커진다면 이 연산을 병렬로 처리하는 것이 좋을 것이다.
     * 이를 병렬 스트림으로 변환해보자.
     * @param n
     * @return
     */
    long sequentialSum(long n) {
        /* java 8 */
        return Stream.iterate(1L, i -> i + 1)
                    .limit(n)
                    .reduce(0L, Long::sum);

        /* java 7 */
        /*
        long result = 0;

        for (long i = 0; i <= n; i++) {
            result += i;
        }

        return result;
        */
    }

    /**
     * 리듀싱 연산을 여러 청크에 병렬로 수행할 수 있다.
     * 리듀싱 연산으로 생성된 부분 결과를 다시 리듀싱 연산으로 합쳐서 전체 스트림의 리듀싱 결과를 도출한다.
     * @param n
     * @return
     */
    long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1)
                    .limit(n)
                    .parallel() /* 스트림을 병렬 스트림으로 변환 */
                    .reduce(0L, Long::sum);
    }
}
