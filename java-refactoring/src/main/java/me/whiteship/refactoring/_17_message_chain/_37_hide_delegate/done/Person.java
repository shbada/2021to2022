package me.whiteship.refactoring._17_message_chain._37_hide_delegate.done;

public class Person {

    private String name;

    private Department department;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    /**
     * Person 으로 이동
     * @return
     */
    public Person getManager() {
        // 위임을 숨긴다. (person.getManager() 로 클라이언트는 사용한다)
        return getDepartment().getManager();
    }
}
