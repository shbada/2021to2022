package ch2.인터페이스와클래스.exam2;

public interface Human {
    public int getAge();

    public String getName();

    public String getSex();

    // default 메서드
    default String getInformation() {
        return "Human";
    }
}
