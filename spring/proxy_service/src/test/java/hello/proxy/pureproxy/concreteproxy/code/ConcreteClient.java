package hello.proxy.pureproxy.concreteproxy.code;

public class ConcreteClient {
    private ConcreteLogic concreteLogic;

    public ConcreteClient(ConcreteLogic concreteLogic) { // 프록시 객체가 들어있음
        this.concreteLogic = concreteLogic;
    }

    public void execute() {
        concreteLogic.operation(); // 프록시 객체의 operation 을 호출
    }
}
