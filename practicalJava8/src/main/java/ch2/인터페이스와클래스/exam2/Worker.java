package ch2.인터페이스와클래스.exam2;

public class Worker implements Female, Male {
    public static final String MALE = "male";
    public static final String FEMALE = "female";

    private int age;
    private String name;
    private String sex;

    public Worker(int age, String name, String sex) {
        this.age = age;
        this.name = name;
        this.sex = sex;
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

     만약, 인터페이스의 default 메서드를 호출하려면 super 키워드를 사용하면된다.

     * @return
     */
    @Override
    public String getSex() {
        String returnValue = null;

        if (sex != null && "male".equals(sex)) {
            // returnValue = Worker.MALE;
            returnValue = Male.super.getSex(); // 인터페이스의 default 메서드 호출 (호출을 위해서는 implements 키워드 필수)
        } else {
            returnValue = Worker.FEMALE;
        }

        return returnValue;
    }

    public static void main(String[] args) {
        /* static 문장 형태에서는 super 키워드는 사용할 수 없다 */
        // Female.super.getSex();
    }
    /**
     * Male.super.getSex()처럼 인터페이스의 default 메서드 호출 방법
     1) 클래스 작성시 구현할 인터페이스의 default 메서드를 오버라이드하지 않았을 경우 직접 default 메서드 호출
     2) 클래스의 생성자 혹은 메서드에서 구현할 인터페이스의 default 메서드는 super 키워드를 이용해서 호출할 수 있다.

     동일한 메서드거 클래스 , 인터페이스에 둘다 있을 경우 extends 클래스 implements 인터페이스일 때

     1) 클래스가 인터페이스에 대해 우선순위를 가진다. 동일한 메서드 호출시 클래스의 메서드가 호출된다.
     2) 위의 조건을 제외하고 상속 관계에 있을 경우에는 하위 클래스/인터페이스가 상위 클래스/인터페이스보다 우선 호출된다.
     3) 위의 두가지 경우를 제외하고 메서드 호출시 어떤 메서드를 호출해야 할지 모호할 경우 컴파일 에러가 발생할 수 있고,
        반드시 호출하고자 하는 클래스 혹은 인터페이스를 명확하게 지정해야한다.

     자바 8에서 다이아몬트 상속과 다중 상속 발생시, 자바8 이상에서 클래스-인터페이스를 설계하고 활용하자.
     또한 위 3가지 규칙을 반드시 이해하자.
     */
}
