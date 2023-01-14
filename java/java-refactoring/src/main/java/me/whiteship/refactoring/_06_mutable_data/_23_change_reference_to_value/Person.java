package me.whiteship.refactoring._06_mutable_data._23_change_reference_to_value;

/**
 * 참조를 값으로 바꾸기
 * - 값 객체는 객체가 가진 필드의 값으로 동일성을 확인한다.
 * - 값 객체는 변하지 않는다.
 * - 어떤 객체의 변경 내역을 다른 곳으로 전파하고 싶다면 래퍼런드, 아니라면 값 객체를 사용한다.
 */
public class Person {

    private TelephoneNumber officeTelephoneNumber;

    public String officeAreaCode() {
        return this.officeTelephoneNumber.areaCode();
    }

    public void officeAreaCode(String areaCode) {
        this.officeTelephoneNumber.areaCode(areaCode);
    }

    public String officeNumber() {
        return this.officeTelephoneNumber.number();
    }

    public void officeNumber(String number) {
        this.officeTelephoneNumber.number(number);
    }

}
