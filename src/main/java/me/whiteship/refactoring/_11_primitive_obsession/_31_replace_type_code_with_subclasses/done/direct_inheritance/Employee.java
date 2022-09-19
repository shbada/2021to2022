package me.whiteship.refactoring._11_primitive_obsession._31_replace_type_code_with_subclasses.done.direct_inheritance;

import java.util.List;

/**
 * 직접 상속 사용하는 경우
 */
public abstract class Employee {

    private String name;

//    private String type;

    protected Employee(String name) {
//        this.validate(type);
        this.name = name;
//        this.type = type;
    }

    public static Employee createEmployee(String name, String type) {
        switch (type) {
            case  "engineer" : return new Engineer(name);
            case  "manager" : return new Manager(name);
            case  "salesman" : return new Salesman(name);
            default : throw new IllegalArgumentException(type);
        }
    }

    // switch문으로 이동
//    private void validate(String type) {
//        List<String> legalTypes = List.of("engineer", "manager", "salesman");
//
//        if (!legalTypes.contains(type)) {
//            throw new IllegalArgumentException(type);
//        }
//    }

    protected abstract String getType();

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", type='" + getType() + '\'' +
                '}';
    }
}
