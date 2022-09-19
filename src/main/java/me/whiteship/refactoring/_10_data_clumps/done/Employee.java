package me.whiteship.refactoring._10_data_clumps.done;

public class Employee {

    private String name;

    // 필드 옮김 (TelephoneNumber)
//    private String personalAreaCode;
//
//    private String personalNumber;

    private final TelephoneNumber personalPhoneNumber;

//    public Employee(String name, String personalAreaCode, String personalNumber) {
//        this.name = name;
//        this.personalAreaCode = personalAreaCode;
//        this.personalNumber = personalNumber;
//    }

    public Employee(String name, TelephoneNumber personalPhoneNumber) {
        this.name = name;
        this.personalPhoneNumber = personalPhoneNumber;
    }

    public String personalPhoneNumber() {
        return this.personalPhoneNumber.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 아래 메서드가 필요하다면 TelephoneNumber의 메서드를 호출하는 로직으로 가도된다.
//    public String getPersonalAreaCode() {
//        return personalAreaCode;
//    }
//
//    public void setPersonalAreaCode(String personalAreaCode) {
//        this.personalAreaCode = personalAreaCode;
//    }
//
//    public String getPersonalNumber() {
//        return personalNumber;
//    }
//
//    public void setPersonalNumber(String personalNumber) {
//        this.personalNumber = personalNumber;
//    }
}
