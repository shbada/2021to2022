package me.whiteship.refactoring._10_data_clumps.done;

public class Office {

    private String location;

//    private String officeAreCode;
//
//    private String officeNumber;

    private final TelephoneNumber officePhoneNumber;

//    public Office(String location, String officeAreCode, String officeNumber) {
//        this.location = location;
//        this.officeAreCode = officeAreCode;
//        this.officeNumber = officeNumber;
//    }

    public Office(String location, TelephoneNumber officePhoneNumber) {
        this.location = location;
        this.officePhoneNumber = officePhoneNumber;
    }

    public String officePhoneNumber() {
        return this.officePhoneNumber.toString();
    }

//    public String getOfficeAreCode() {
//        return officeAreCode;
//    }
//
//    public void setOfficeAreCode(String officeAreCode) {
//        this.officeAreCode = officeAreCode;
//    }
//
//    public String getOfficeNumber() {
//        return officeNumber;
//    }
//
//    public void setOfficeNumber(String officeNumber) {
//        this.officeNumber = officeNumber;
//    }
}
