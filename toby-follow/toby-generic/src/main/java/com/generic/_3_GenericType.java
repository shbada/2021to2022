package com.generic;

public class _3_GenericType {
   static <T> void print(T t) { // 타입 파라미터 정의, static 가능
       System.out.println(t.toString());
   }

    public static void main(String[] args) {
        new _3_GenericType().print("Hello");
        new _3_GenericType().print(1);
    }
}
