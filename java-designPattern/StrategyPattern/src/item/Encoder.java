package item;

public class Encoder {
    /* 전략 주입 받기 */
    private EncodingStrategy encodingStrategy;

    public String getMessage(String message) {
        // 각각의 전략에 따라 결과가 달라지는 부분
        return this.encodingStrategy.encode(message);
    }

    public void setEncodingStrategy(EncodingStrategy encodingStrategy) {
        this.encodingStrategy = encodingStrategy;
    }
}
