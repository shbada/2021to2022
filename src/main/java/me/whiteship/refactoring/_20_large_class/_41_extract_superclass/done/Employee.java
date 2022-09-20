package me.whiteship.refactoring._20_large_class._41_extract_superclass.done;

// 상속으로 구현
public class Employee extends Party {

    private Integer id;

    private double monthlyCost;

    public Employee(String name) {
        super(name);
    }

    // Party 로 올라갔으므로 제거
//    public double annualCost() {
//        return this.monthlyCost * 12;
//    }

    @Override
    protected double monthlyCost() {
        return monthlyCost;
    }

    public Integer getId() {
        return id;
    }
}
