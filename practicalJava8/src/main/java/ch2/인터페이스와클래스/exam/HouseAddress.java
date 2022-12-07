package ch2.인터페이스와클래스.exam;

public interface HouseAddress {
    public static final String DefaultCountry = "Korea";

    // 우편번호 리턴
    public String getPostCode();

    // 주소 정보를 리턴
    public String getAddress();

    // 상세 정보르 리턴
    public String getDetailAddress();

    /** HouseAddress를 KoreaHouseAddress에서 구현 후, 만약, 추후 메소드를 추가하게된다면?
     */
    // 추가) 국가 코드 리턴
    // public String getCountryCode();
    /** 추가 직후, KoreaHouseAddress.java에서는 에러가 발생한다. 해당 인터페이스를 구현한 모든 클래스에서 에러가 발생한다 */
    /**
     * 이때 컴파일 에러가 발생
     * 인터페이스만 개별 파일로 반영시, 기존 존재하던 메서드는 정상 실행되지만 새로 추가된 메서드 호출시 에러 발생 (NoSuchMethodError)
     */

    /**
     해결방안 ------------------- default 메서드 선언
     -> 여기서, default 메서드는 명시적이여야 하는 이유는 default가 없으면 기존 인터페이스 메서드와 구분하기 어렵기 때문이다.
     * */
    // 국가코드를 리턴한다
    default public String getCountryCode() {
        /* static, default 메서드는 코드 구현이 필수이며, 구현하지 않을시 컴파일 에러가 발생한다 */
        return HouseAddress.DefaultCountry;
    }
}
