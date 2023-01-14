package item2;

/**
 * Coffee 를 포함한 클래스가 됨
 * Coffee 를 상속받은 Decorator 을 상속받았으므로
 */
public class Latte extends Decorator {
    // 상위클래스에 default 생성자가 없기 때문에 상속받았을때 하위클래스의 매개변수가 있는 생성자에서 super 을 명시적으로 호출해줘야한다.
    public Latte(Coffee coffee) {
        super(coffee);
    }

    @Override
    public void brewing() {
        super.brewing();
        System.out.println(" Adding Milk");
    }
}
