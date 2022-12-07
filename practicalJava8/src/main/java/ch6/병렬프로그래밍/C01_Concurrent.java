package ch6.병렬프로그래밍;

public class C01_Concurrent {
    public static void main(String[] args) {
        /**
         * 컨터런트 API
         * 병렬 애플리케이션에서 데이터의 동기화와 정합성을 확보하기 위해 Lock 객체를 제공하며 이를 통해 잠금 기능을 사용할 수 있다.
         * Executors 클래스를 제공한다. (java.util.concurrent 패키지에 포함) - 대량데이터의 병렬처리에 적합하다.
         * 동기화를 위한 synchronized 키워드 사용을 최소화하여 성능을 확보하면서 메모리 정합성 에러를 방지할 수 있다.
         * ThreadLocalRandom 클래스를 이용해서 멀티 스레드 환경에서 효율적인 난수를 생성하는 기능을 제공한다.
         *
         * 패키지 3가지
         * java.util.concurrent : 가장 많이 사용, 대부분의 컨커런트 API는 여기에 포함되어있다.
         * java.util.concurrent.atomic
         * java.util.concurrent.locks
         */

        /**
         * 컨터런트 API의 인터페이스와 클래스의 분류
         *
         * 1) 실행자 Executors
         * = 컨커런트 API에서 작업을 실행하는 역할을 하며 인터페이스와 인터페이스를 구현한 클래스로 구성되어있다.
         * = 비동기처리, 스레드 풀, 테스크 프레임워크 등을 쉽게 구현할 수 있다.
         *
         * 2) 큐 Queues
         * = 컬렉션 프레임워크에서 제공하는 선입선출 방식의 데이터 처리 흐름이다.
         *
         * 3) 타이밍 Timing
         * = 스레드를 잘 실행하고 종료해서 한정된 자원을 최대한 효율적으로 활용해야한다. 멀티 스레드에 타임아웃 기능을 제공한다.
         *
         * 4) 동기화 Synchronizers
         * = 동시에 실행할 수 있는 스레드 크기를 제한하고, 스레드를 많이 생성하다보면 성능이 떨어지고 처리 속도도 느려지는데 이를 방지한다.
         *
         * 5) 컨커런트 컬렉션 Concurrent Collections
         * = List 혹은 Map 형 데이터를 다루기 위해 제공하는 인터페이스와 클래스들이다.
         * = 멀티 스레드에서 안전하고, 멀티 스레드 환경에서 더 좋은 성능을 발휘한다.
         * = 데이터의 업데이트, 삭제 작업 등이 많다면 HashMap 이나 ArrayList 보다 컨커런트 컬렉션을 사용하는 것이 좋다.
         * = 동시 처리를 보장한다.
         *
         * 6) 메모리 정합성 관련 속성 (Memory Consistency Properties)
         * = 자바 언어 스펙을 보면 공유되는 변수의 값에 대해서 멀티 스레드가 읽고 쓰기를 할때 데이터의 정합성을 보장하기 위해서
         *   synchronized 나 volatile 키워드로 보호해야한다고 기술되어있다.
         */
    }
}
