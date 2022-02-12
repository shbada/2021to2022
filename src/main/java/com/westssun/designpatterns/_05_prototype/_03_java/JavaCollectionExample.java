package com.westssun.designpatterns._05_prototype._03_java;

import java.util.ArrayList;
import java.util.List;

public class JavaCollectionExample {

    public static void main(String[] args) {
        Student keesun = new Student("keesun");
        Student whiteship = new Student("whiteship");

        List<Student> students = new ArrayList<>();
        students.add(keesun);
        students.add(whiteship);

        // 이 방식은 잘 쓰이지 않는다.
        // List<Student> 타입을 많이 쓰므로 (유연성)
        // ArrayList<Student> students1 = new ArrayList<>();
        // Object clone1 = students1.clone();

        // studnet 의 element 로 새로운 clone 컬렉션을 만든다.
        // 얕은복사
        List<Student> clone = new ArrayList<>(students);

        // 변경해보자.
        students.get(0).setName("aaaaa");

        System.out.println(clone); // [Student{name='aaaaa'}, Student{name='whiteship'}]
        System.out.println(students); // [Student{name='aaaaa'}, Student{name='whiteship'}]
    }
}
