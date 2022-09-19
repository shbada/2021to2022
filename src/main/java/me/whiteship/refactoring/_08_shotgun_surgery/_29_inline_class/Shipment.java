package me.whiteship.refactoring._08_shotgun_surgery._29_inline_class;

/**
 * 리팩토링 29. 클래스 인라인 (Inline Class)
 * - 클래스 추출하기의  반대
 * - 클래스의 책임을 옮기다보면 클래스의 존재 이유가 빈약해지는 경우가 발생할 수 있다.
 * - 두개의 클래스를 여러 클래스로 나누는 경우, "클래스 인라인"을 먼저 적용해서 두 클래스의 코드를 한곳으로 모으고,
 * "클래스 추출하기"를 적용해서 새롭게 분리하는 리팩토링을 적용할 수 있다.
 */
public class Shipment {

    private TrackingInformation trackingInformation;

    public Shipment(TrackingInformation trackingInformation) {
        this.trackingInformation = trackingInformation;
    }

    public TrackingInformation getTrackingInformation() {
        return trackingInformation;
    }

    public void setTrackingInformation(TrackingInformation trackingInformation) {
        this.trackingInformation = trackingInformation;
    }

    public String getTrackingInfo() {
        return this.trackingInformation.display();
    }
}
