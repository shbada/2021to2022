package com.java.effective.item38;

public class Test {
    public static void main(String[] args) {
        double x = Double.parseDouble("10");
        double y = Double.parseDouble("2");

        test(ExtendedOperation.class, x, y);
    }

    /**
     * Class 객체가 열거타입인 동시에 Operation의 하위타입이어야 한다.
     * @param opEnumType
     * @param x
     * @param y
     * @param <T>
     */
    private static <T extends Enum<T> & Operation> void test(Class<T> opEnumType, double x, double y) {
        for (Operation op : opEnumType.getEnumConstants()) {
            System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
        }

    }
}
