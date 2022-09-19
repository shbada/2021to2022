package me.whiteship.refactoring._11_primitive_obsession._32_replace_conditional_with_polymorphism.done.swtiches;

import java.util.List;

public abstract class Employee { // 테스트 코드 수정!

//    protected String type;

    protected List<String> availableProjects;

    public Employee(List<String> availableProjects) {
//        this.type = type;
        this.availableProjects = availableProjects;
    }

    public Employee() { // 기본 생성자
    }

    public abstract int vacationHours();

    // PartTime, TemporalTime 에서 중복으로 사용되는 메서드여서 상위로 올렸다.
    public boolean canAccessTo(String project) {
        return this.availableProjects.contains(project);
    }
}
