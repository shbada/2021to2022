package item;

public class NormalStrategy implements EncodingStrategy {
    /**
     * 전략 구현
     * @param text
     * @return
     */
    @Override
    public String encode(String text) {
        return text;
    }
}
