package ModernInJava8.ch3_lamda;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferedReaderProcessor {
    /* 함수형 인터페이스는 오직 1개의 추상메서드만 선언 가능하다 */
    String process(BufferedReader b) throws IOException;
}
