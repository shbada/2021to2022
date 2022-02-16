package com.westssun.designpatterns._02_structural_patterns._09_decorator._03_java;

import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponseWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DecoratorInJava {

    public static void main(String[] args) {
        // Collections가 제공하는 데코레이터 메소드
        ArrayList list = new ArrayList<>(); // 타입선언이 없음
        list.add(new Book());

        // checkedxx : type check
        // Book type 만 받는다.
        List books = Collections.checkedList(list, Book.class);

        // list.add(new Item());// 리스트에는 들어가는데,
//        books.add(new Item()); // 여긴 에러가 발생한다.

        // 해당 Collection 을 불변취급
        List unmodifiableList = Collections.unmodifiableList(list);
        list.add(new Item()); // 기존 리스트는 추가 가능
        unmodifiableList.add(new Book()); // 추가가 불가능함


        // 서블릿 요청 또는 응답 랩퍼
        // HttpServletRequest 확장이 가능하다.
        HttpServletRequestWrapper requestWrapper;
        HttpServletResponseWrapper responseWrapper;
    }

    private static class Book {

    }

    private static class Item {

    }
}
