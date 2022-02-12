package com.westssun.designpatterns._05_prototype._03_java;

import lombok.Setter;

public class Student {

    @Setter
    String name;

    public Student(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
