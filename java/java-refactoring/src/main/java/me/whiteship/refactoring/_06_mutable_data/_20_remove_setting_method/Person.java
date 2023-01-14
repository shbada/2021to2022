package me.whiteship.refactoring._06_mutable_data._20_remove_setting_method;

/**
 * Setter
 * 객체 생성시 처음 설정된 값이 변경될 필요가 없다면 해당 값을 설정할 수 있는 생성자를 만들고
 * Setter을 제거해서 변경될 수 있는 가능성을 제거해야한다.
 */
public class Person {

    private String name;

    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
