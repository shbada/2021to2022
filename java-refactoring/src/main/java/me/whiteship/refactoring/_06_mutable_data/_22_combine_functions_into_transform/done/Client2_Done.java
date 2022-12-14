package me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform.done;

import me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform.Reading;

public class Client2_Done extends ReadingClient {

    private double base;
    private double taxableCharge;

    public Client2_Done(Reading reading) {
        EnrichReading enrichReading = enrichReading(reading);
        this.base = enrichReading.baseCharge();
        this.taxableCharge = enrichReading.taxableCharge();
    }

//    private double taxThreshold(Year year) {
//        return 5;
//    }
//
//    private double baseRate(Month month, Year year) {
//        return 10;
//    }

    public double getBase() {
        return base;
    }

    public double getTaxableCharge() {
        return taxableCharge;
    }
}
