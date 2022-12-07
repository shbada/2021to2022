package item2;

/**
 * Decorator
 * abstract : 상속을 위한 상위클래스로만 쓴다.
 */
public abstract class Decorator extends Coffee {
    Coffee coffee;

    /* Coffee 를 상속받은 아이는 올 수 있다 */
    public Decorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public void brewing() {
        coffee.brewing();
    }
}
