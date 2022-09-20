package me.whiteship.refactoring._20_large_class._41_extract_superclass.done;

import java.util.List;

public class Department extends Party {

    private List<Employee> staff;

    public Department(String name) {
        super(name);
    }

    public List<Employee> getStaff() {
        return staff;
    }

    @Override
    public double monthlyCost() {
        return this.staff.stream().mapToDouble(Employee::monthlyCost).sum();
    }

    // Party 로 올리자.
//    public double annualCost() {
//        return this.monthlyCost() * 12;
//    }

    public int headCount() {
        return this.staff.size();
    }

}
