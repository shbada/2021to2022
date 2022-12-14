package me.whiteship.refactoring._06_mutable_data._21_replace_derived_variable_with_query;

import java.util.ArrayList;
import java.util.List;

public class ProductionPlan_Done {

    private double production;
    private List<Double> adjustments = new ArrayList<>();

    public void applyAdjustment(double adjustment) {
        this.adjustments.add(adjustment);
        // 파생변수
//        this.production += adjustment;
    }

    public double getProduction() {
        // 계산 메서드 생성
        assert this.production == calculatedProduction();
        return this.production;
    }

    private double calculatedProduction() {
        return this.adjustments.stream().reduce((double) 0, Double::sum);
    }
}
