package ch4.람다와함수형인터페이스;

import java.util.function.Supplier;

public class SupplierExample {
    /**
     * Consumer 인터페이스와 반대이다.
     * 입력 파라미터는 없고 리턴 타입만 존재한다.
     * 입력 없이 출력만 있어서 공급자라는 이름을 사용하였고, get 메서드를 사용할 수 있다.
     */

    public static String execute(Supplier<String> supplier) {
        return supplier.get();
    }

    public static void main(String[] args) {
        String version = "2.0";
        SupplierExample.execute(() -> {return version;});
    }
}
