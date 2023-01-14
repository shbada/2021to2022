package hello.springtx.order;

/**
 * Exception 을 상속 받아서 체크 예외가 된다.
 */
public class NotEnoughMoneyException extends Exception {
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
