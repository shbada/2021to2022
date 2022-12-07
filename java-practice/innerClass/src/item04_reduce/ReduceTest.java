package item04_reduce;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.BinaryOperator;

public class ReduceTest {
    public static void main(String[] args) {
        String values[] = {"Hello", "World", "!"};

        /* 가장 긴 length 문자열 추출 */
        String reduce = Arrays.stream(values).reduce("", (s1, s2) -> {
                            if (s1.getBytes().length >= s2.getBytes().length) {
                                return s1;
                            } else {
                                return s2;
                            }
                        });

        /* lamda */
        String reduce2 = Arrays.stream(values).reduce(new CompareString()).get();
        System.out.println(reduce2);
    }
}

class CompareString implements BinaryOperator<String> {

    @Override
    public String apply(String s1, String s2) {
        if (s1.getBytes().length >= s2.getBytes().length) {
            return s1;
        } else {
            return s2;
        }
    }
}