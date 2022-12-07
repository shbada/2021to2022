package ch4.람다와함수형인터페이스;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class OperatorExample {
    public static void main(String[] args) {
        /** UnaryOperator */
        UnaryOperator<Integer> operatorA = (Integer t) -> t * 2;
        System.out.println(operatorA.apply(1));

        /** BinaryOperator */
        BinaryOperator<Integer> operatorB = (Integer a, Integer b) -> a + b;
        System.out.println(operatorB.apply(1, 2));
    }
}
