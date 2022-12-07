package item;

import java.util.Base64;

public class Base64Strategy implements EncodingStrategy {
    /**
     * 전략 구현
     * @param text
     * @return
     */
    @Override
    public String encode(String text) {
        return Base64.getEncoder().encodeToString(text.getBytes());
    }
}
