package me.whiteship.refactoring._20_large_class._41_extract_superclass.done;

public abstract class Party {
    // Department, Employee 에서 위로 올렸다. (필드 올리기)
    private String name;

    public Party(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double annualCost() {
        return this.monthlyCost() * 12;
    }

    // 추상메서드
    protected abstract double monthlyCost();
}
