package me.whiteship.refactoring._07_divergent_change._26_extract_class.done;

public class TelephoneNumber {// officeAreaCode, officeNumber 가 연관된 데이터
    /*
        TelephoneNumber 에 이동되면서 필드 이름이 애매해졌다.
    */
    private String areaCode;
    private String number;

    public TelephoneNumber() {
    }

    public String areaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String number() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "TelephoneNumber{" +
                "areaCode='" + areaCode + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}