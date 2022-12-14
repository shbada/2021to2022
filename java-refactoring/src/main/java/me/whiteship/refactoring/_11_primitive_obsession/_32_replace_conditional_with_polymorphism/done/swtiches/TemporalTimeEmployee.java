package me.whiteship.refactoring._11_primitive_obsession._32_replace_conditional_with_polymorphism.done.swtiches;

import java.util.List;

public class TemporalTimeEmployee extends Employee {
    public TemporalTimeEmployee(List<String> availableProjects) {
        super(availableProjects);
    }

    @Override
    public int vacationHours() {
        return 32;
    }

    // 중복 코드는 위로 올리자.
//    @Override
//    public boolean canAccessTo(String project) {
//        return this.availableProjects.contains(project);
//    }
}
