package ch2.인터페이스와클래스.exam2;

public interface Female extends Human {
    public static String SEX = "female";

    /* default 메서드 오버라이드는 필수가 아님 */
    @Override
    default String getSex() {
        return Female.SEX;
    }

    /**
     * 하위 인터페이스는 상위 인터페이스의 메서드를 오버라이드 해서 default 메서드로 재정의할 수 있다.
     */
}
