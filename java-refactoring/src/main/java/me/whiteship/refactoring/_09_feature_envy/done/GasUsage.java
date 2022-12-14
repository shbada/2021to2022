package me.whiteship.refactoring._09_feature_envy.done;

public class GasUsage {

    private double amount;

    private double pricePerUnit;

    public GasUsage(double amount, double pricePerUnit) {
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
    }

    public double getAmount() {
        return amount;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    // move
    double getGasBill() {
        return this.getAmount() * this.getPricePerUnit();
    }
}
