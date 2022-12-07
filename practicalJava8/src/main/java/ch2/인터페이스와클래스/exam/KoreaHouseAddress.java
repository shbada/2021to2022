package ch2.인터페이스와클래스.exam;

public class KoreaHouseAddress implements HouseAddress {
    private String postCode;
    private String address;
    private String detailAddress;

    public KoreaHouseAddress(String postCode, String address, String detailAddress) {
        this.postCode = postCode;
        this.address = address;
        this.detailAddress = detailAddress;
    }

    @Override
    public String getPostCode() {
        return postCode;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * default 메서드 오버라이드
     * @return
     */
    @Override
    public String getCountryCode() {
        /* java8 추가) super 키워드를 이용해서 default 메서드를 호출하는 기능 사용 */
        return HouseAddress.super.getCountryCode();
    }
}
