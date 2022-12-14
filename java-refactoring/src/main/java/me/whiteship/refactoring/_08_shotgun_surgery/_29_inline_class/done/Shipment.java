package me.whiteship.refactoring._08_shotgun_surgery._29_inline_class.done;

/**
 * 리팩토링 29. 클래스 인라인 (Inline Class)
 * - 클래스 추출하기의  반대
 * - 클래스의 책임을 옮기다보면 클래스의 존재 이유가 빈약해지는 경우가 발생할 수 있다.
 * - 두개의 클래스를 여러 클래스로 나누는 경우, "클래스 인라인"을 먼저 적용해서 두 클래스의 코드를 한곳으로 모으고,
 * "클래스 추출하기"를 적용해서 새롭게 분리하는 리팩토링을 적용할 수 있다.
 */
public class Shipment {

    private TrackingInformation trackingInformation;

    // 1. 필드 옮기기
    private String shippingCompany;

    private String trackingNumber;

//    public Shipment(TrackingInformation trackingInformation) {
//        this.trackingInformation = trackingInformation;
//    }

    // 2. 생성자 생성
    public Shipment(TrackingInformation trackingInformation, String trackingNumber) {
        this.trackingInformation = trackingInformation;
        this.trackingNumber = trackingNumber;
    }

//    public TrackingInformation getTrackingInformation() {
//        return trackingInformation;
//    }
//
//    public void setTrackingInformation(TrackingInformation trackingInformation) {
//        this.trackingInformation = trackingInformation;
//    }

//    public String getTrackingInfo() {
//        return this.display();
//    }

    // 3. 메서드 옮기기
    public String getTrackingInfo() { // display -> getTrackingInfo
        return this.shippingCompany + ": " + this.trackingNumber;
    }

    public String getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
