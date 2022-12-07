package ch5.스트림API;

public class C04_StreamOperation {
    public static void main(String[] args) {
        /**
         * 스트림 연산의 파이프라인
         * 1) filter : 원하는 데이터 추출
         * 2) sorted : 정렬
         * 3) map : 데이터 변환
         * 4) collect : List 객체로 리턴
         *
         * => [중간연산] filter, sorted, map 은 주어진 데이터에 대해 연산 작업을 수행해서 또다른 스트림 객체를 리턴한다.
         * => [최종연산] collect 는 스트림 데이터를 모두 소모하고 스트림을 종료한다.
         *
         * 중간연산자, 최종연산자는 함수형 인터페이스를 기반으로 하고있다. (람다 표현식으로 동작을 정의할 수 있다.)
         *
         * 스트림 객체 생성 단계 -> 중간 연산 단계 (스트림의 데이터 필터링, 정렬, 변환하는 단계를 거친다) -> 최종 연산 단계 (데이터 소모 후 스트림 종료한다)
         *
         * 중간연산의 메서드
         * > concat, distinct, filter, limit, map, sorted 등
         *
         * 최종연산의 메서드
         * > count, collect, forEach
         */
    }
}
