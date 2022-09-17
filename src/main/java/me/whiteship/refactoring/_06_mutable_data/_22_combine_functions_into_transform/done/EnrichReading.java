package me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform.done;

import me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform.Reading;

public record EnrichReading(Reading reading, double baseCharge, double taxableCharge) {}
