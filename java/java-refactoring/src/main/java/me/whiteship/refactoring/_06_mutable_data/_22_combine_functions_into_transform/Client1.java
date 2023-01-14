package me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform;

import java.time.Month;
import java.time.Year;

/**
 * 여러 함수를 변환 함수로 묶기
 * - 여러 파생 변수를 만들어내는 함수가 여러곳에서 만들어지고 사용된다면,
 * 그러한 파생 변수를 "변환 함수"를 통해 한곳으로 모아둘 수 있다.
 * - 소스 데이터가 변경될 수 있다면 "여러 함수를 클래스로 묶기"를 사용하자
 * - 변환 함수를 사용해서 불변 데이터의 필드로 생성해두고 재사용할 수도 있다.
 */
public class Client1 {

    double baseCharge;

    public Client1(Reading reading) {
        this.baseCharge = baseRate(reading.month(), reading.year()) * reading.quantity();
    }

    private double baseRate(Month month, Year year) {
        return 10;
    }

    public double getBaseCharge() {
        return baseCharge;
    }
}
