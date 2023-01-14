package hello.core.singleton;

public class SingletonService {
    // static level (class 올라갈때 딱 한번 실행)
    private static final SingletonService instance = new SingletonService();

    /** 객체 생성을 방지하는 private 생성자 */
    private SingletonService() {}

    // static level (해당 메서드를 통해서만 객체를 얻을 수 있음 -> 항상 같은 객체를 반환)
    public static SingletonService getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        // 이렇게 생성됨을 막아야해서 private 생성자 사용 (같은 클래스 말고 다른 클래스일때)
        // SingletonService singletonService1 = new SingletonService();
        // SingletonService singletonService2 = new SingletonService();
    }
}
