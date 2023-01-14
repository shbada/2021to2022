package me.whiteship.refactoring._09_feature_envy.done;

public class Bill {

    private ElectricityUsage electricityUsage;

    private GasUsage gasUsage;

    /**
     * 기능 욕심
     * ElectricityUsage, GasUsage 에서 직접 계산해서 제공해줄 수도 있는데, 참조가 너무 많다.
     * @return
     */
    public double calculateBill() {
        // 1) 나누고 각 책임에 맞는 클래스로 옮긴다.
        // 2) 인라인 메서드
        return electricityUsage.getElecticityBill() + gasUsage.getGasBill();
    }

    // 이 추출한 메서드들을 각자에 옮긴다.
//    private double getGasBill() {
//        var gasBill = gasUsage.getAmount() * gasUsage.getPricePerUnit();
//        return gasBill;
//    }
//
//    private double getElecticityBill() {
//        return electricityUsage.getAmount() * electricityUsage.getPricePerUnit();
//    }

}
