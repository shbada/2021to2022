package me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform.done;

import me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform.Reading;

import java.time.Month;
import java.time.Year;

public class ReadingClient {
    protected double taxThreshold(Year year) {
        return 5;
    }

    protected double baseRate(Month month, Year year) {
        return 10;
    }

    protected EnrichReading enrichReading(Reading reading) {
        return new EnrichReading(reading, baseCharge(reading), taxableCharge(reading)); // 새로운 타입으로 변환해서 넘길 수 있다.
    }

    private double taxableCharge(Reading reading) {
        return Math.max(0, baseCharge(reading)) - taxThreshold(reading.year());
    }

    /**
     * 메서드 공통 처리
     * @param reading
     * @return
     */
    private double baseCharge(Reading reading) {
        return baseRate(reading.month(), reading.year()) * reading.quantity();
    }
}
