package me.whiteship.refactoring._07_divergent_change._24_split_phase.done;

/**
 * 필드 옮기기
 * @param basePrice
 * @param discount
 * @param quantity
 */
public record PriceData(double basePrice, double discount, int quantity) {
}
