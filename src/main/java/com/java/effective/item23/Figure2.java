package com.java.effective.item23;

/**
 * 추상클래스 (루트클래스)
 */
abstract class Figure {
    abstract double area();
}

/**
 * 구현클래스 1
 */
class Rectangle extends Figure {
    /* 각자의 의미에 해당하는 데이터 필드 */
    final double length;
    final double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width  = width;
    }

    /* 추상 메서드를 각자의 의미에 맞게 구현 */
    @Override double area() { return length * width; }
}

/**
 * 구현클래스 2
 */
class Circle extends Figure {
    final double radius;

    Circle(double radius) { this.radius = radius; }

    @Override double area() { return Math.PI * (radius * radius); }
}

/**
 * 구현클래스3 : 추가요건) 정사각형
 */
class Square extends Rectangle {
    Square(double side) {
        super(side, side);
    }
}
