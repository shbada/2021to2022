package me.whiteship.refactoring._06_mutable_data._22_combine_functions_into_transform;

import java.time.Month;
import java.time.Year;

/**
 * 불변 데이터
 * 참조만 가능, 변경은 불가능
 */
public record Reading(String customer, double quantity, Month month, Year year) {
}
