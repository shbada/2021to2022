package me.whiteship.refactoring._07_divergent_change._24_split_phase.done;

import me.whiteship.refactoring._07_divergent_change._24_split_phase.Product;
import me.whiteship.refactoring._07_divergent_change._24_split_phase.ShippingMethod;

public class PriceOrder {

    public double priceOrder(Product product, int quantity, ShippingMethod shippingMethod) {
        final PriceData priceData = calculatePriceData(product, quantity);

        // (basePrice, discount) : base 값과 관련
        // 관련있는걸 priceData로 묶고, 그 외의 shippingMethod 는 별도로 전달
        return applyShpping(priceData, shippingMethod);
    }

    private static PriceData calculatePriceData(Product product, int quantity) {
        // base
        final double basePrice = product.basePrice() * quantity;

        final double discount = Math.max(quantity - product.discountThreshold(), 0)
                * product.basePrice() * product.discountRate();

        // record 생성
        final PriceData priceData = new PriceData(basePrice, discount, quantity);
        return priceData;
    }

    private static double applyShpping(PriceData priceData, ShippingMethod shippingMethod) {
        // shipping
        final double shippingPerCase = (priceData.basePrice() > shippingMethod.discountThreshold()) ?
                shippingMethod.discountedFee() : shippingMethod.feePerCase();

        final double shippingCost = priceData.quantity() * shippingPerCase;

        final double price = priceData.basePrice() - priceData.discount() + shippingCost;
        return price;
    }
}
