package ch2.인터페이스와클래스.exam2;

public class Worker2 implements Female, Male {
    private int age;
    private String name;

    public Worker2(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getInformation() {
        return name +  ":" + age;
    }

    /**
     * 에러 발생 (Duplicate default method named getSex with the parameters ()...)
      -> Male, Female 인터페이스로부터 상속한 getSex 라는 이름의 default 메서드가 중복되었다는 에러

     해결방법으로 아래처럼 현재 클래스에서 getSex를 오버라이드한다.
     호출해야하는 메서드가 명확해졌기 때문에 컴파일 에러가 발생하지 않는다.

     * @return
     */
    @Override
    public String getSex() {
        return null;
    }
}
