package com.itvillage.chapter04.chapter0402;

import com.itvillage.common.Car;

import java.util.function.Function;

/**
 * Class Name::instance method 메서드 레퍼런스 예
 */
public class _2_ClassNameInstanceMethodExample {
    public static void main(String[] args) {
        // Function<T, R>
        Function<Car, String> f1 = car -> car.getCarName();
        String carName1 = f1.apply(new Car("트래버스"));
        System.out.println(carName1);

        // 람다에서 받은 파라미터의 타입이 Car 이므로 getCarName 으로 추론이 가능
        Function<Car, String> f2 = Car::getCarName;
        String carName2 = f2.apply(new Car("팰리세이드"));
        System.out.println(carName2);
    }
}
