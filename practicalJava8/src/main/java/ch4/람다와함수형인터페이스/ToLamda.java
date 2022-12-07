package ch4.람다와함수형인터페이스;

public class ToLamda {
    public static void main(String[] args) {
        /** 익명클래스 -> 람다로 바꾸는 과정
         *  1. 익명 클래스를 이용해서 메서드 정의
         *  2. 익명 클래스를 생성하기 위해서 선언한 인터페이스 이름 부분 삭제 (메서드 선언 부분만 남게됨)
         *  3. 메서드의 파라미터 목록, 구현한 바디 영역을 제외하고 리턴타입, 메서드명 삭제
         *  4. 람다 문볍에 맞게 '->' 적용
         * */
       /*
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("hello");
                }
            });

            *//* 1단계. 익명 클래스 선언 부분 제거 *//*
            Thread thread2 = new Thread(
                    @Override
                    public void run() {
                    System.out.println("hello");
                }
            );

            *//* 3단계. 메서드 선언 부분 제거 *//*
            Thread thread3 = new Thread(
                    () {
                        System.out.println("hello");
                }
            )

            *//* 최종 결과 *//*
            Thread thread4 = new Thread(
                    () -> System.out.println("hello");
            );
        */
    }
}
