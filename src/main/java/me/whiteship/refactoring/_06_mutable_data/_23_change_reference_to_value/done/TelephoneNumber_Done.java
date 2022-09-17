package me.whiteship.refactoring._06_mutable_data._23_change_reference_to_value.done;

import java.util.Objects;

public class TelephoneNumber_Done {

    private final String areaCode;

    private final String number;

    public TelephoneNumber_Done(String areaCode, String number) {
        this.areaCode = areaCode;
        this.number = number;
    }

    public String areaCode() {
        return areaCode;
    }

//    public void areaCode(String areaCode) {
//        this.areaCode = areaCode;
//    }

    public String number() {
        return number;
    }

//    public void number(String number) {
//        this.number = number;
//    }

    /**
     * equals
     * 값 equals 를 위해 (데이터를 기준으로 판단되도록 선언)
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TelephoneNumber_Done that = (TelephoneNumber_Done) o;
        return Objects.equals(areaCode, that.areaCode) && Objects.equals(number, that.number);
    }


    /**
     * equals 와 함께 반드시 구현되어야한다.
     * 값이 같으면 hashCode도 같고, 다르면 달라야한다.
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(areaCode, number);
    }
}
