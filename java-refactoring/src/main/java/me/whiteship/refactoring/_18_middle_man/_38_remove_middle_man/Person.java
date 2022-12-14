package me.whiteship.refactoring._18_middle_man._38_remove_middle_man;

public class Person {

    private Department department;

    private String name;

    public Person(String name, Department department) {
        this.name = name;
        this.department = department;
    }

    public Person getManager() {
        return this.department.getManager();
    }

    // person.getManager()
    // 중재자 제거하기 수행
    // person.getDepartment().getManager()
    public Department getDepartment() {
        return department;
    }
}
