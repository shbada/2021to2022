package ch2.인터페이스와클래스.exam2;

public interface Male extends Human {
    public static String SEX = "male";

    /* default 메서드 오버라이드는 필수가 아님 */
    @Override
    default String getSex() {
        return Male.SEX;
    }

    /**
     * 하위 인터페이스는 상위 인터페이스의 메서드를 오버라이드 해서 default 메서드로 재정의할 수 있다.
     */
}
