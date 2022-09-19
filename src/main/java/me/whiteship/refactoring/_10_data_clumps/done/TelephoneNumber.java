package me.whiteship.refactoring._10_data_clumps.done;

/**
 * 데이터 뭉치를 클래스로 추출
 */
public class TelephoneNumber {
    // Employee, Office 모두 쓰므로 필드명도 리팩토링
    private String areaCode;

    private String number;

    public TelephoneNumber(String areaCode, String number) {
        this.areaCode = areaCode;
        this.number = number;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return this.areaCode + "-" + this.number;
    }
}
