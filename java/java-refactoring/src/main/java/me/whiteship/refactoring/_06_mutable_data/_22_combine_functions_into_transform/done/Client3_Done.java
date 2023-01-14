package me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform.done;

import me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform.Reading;

public class Client3_Done extends ReadingClient {

    private double basicChargeAmount;

    public Client3_Done(Reading reading) {
        this.basicChargeAmount = enrichReading(reading).baseCharge();
//        this.basicChargeAmount = calculateBaseCharge(reading);
    }

    // ReadingClient 로 move
//    private double calculateBaseCharge(Reading reading) {
//        return baseRate(reading.month(), reading.year()) * reading.quantity();
//    }

//    private double baseRate(Month month, Year year) {
//        return 10;
//    }

    public double getBasicChargeAmount() {
        return basicChargeAmount;
    }
}
