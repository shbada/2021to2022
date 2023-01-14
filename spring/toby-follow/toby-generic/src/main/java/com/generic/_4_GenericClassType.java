package com.generic;

public class _4_GenericClassType<T> {
    /*
       이렇게는 불가능하다.
       클래스의 <T>는 클래스가 만들어질때 인자를 받아오도록 되어있는데,
       static은 인자를 만들지않고 쓰이기 때문에 T가 어떤 타입이 될지를 모른다.
       이는 메서드 레벨의 T로 선언해야한다.
       -> 새로운 타입으로 받겠다는 의미
     */
//   static void print(T t) { // 타입 파라미터 정의, static 가능
//       System.out.println(t.toString());
//   }
    // 허용은 되지만, 클래스의 T와 헷갈리니까 이왕이면 다르게 S 등으로 쓰자.
    static <T> void print(T t) { // 타입 파라미터 정의, static 가능
        System.out.println(t.toString());
    }

    // S: 메서드 레벨에 정의한 타이 파라미터
    // T: 클래스 레벨에 정의한 타입 파라미터
//    <S, T> T print(S s) {
//        System.out.println(s.toString());
//    }

    // 생성자로도 사용 가능하다.
    public <S> _4_GenericClassType(S s) {
        System.out.println(s.toString());
    }

    public static void main(String[] args) {
//        new GenericClassType().print("Hello");
//        new GenericClassType().print(1);
    }
}
