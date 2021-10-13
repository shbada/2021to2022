package item01_innerClass;

class OutClass {
    private int num = 10;
    private static int sNum = 20;
    private InClass inClass;

    public OutClass() { /* OutClass 생성시 아래 내부클래스를 생성 */
        inClass = new InClass();
    }

    private class InClass {
        int iNum = 100;
        /**
         * 외부클래스가 생성된 후 생성되기 때문에 static 선언이 어렵다.
         * static 변수는 외부클래스 생성과 상관없이 생성되기 때문이다.
         */
        // static int sInNum = 500; // error

        void inTest() {
            System.out.println("OutClass num = " + num);
            System.out.println("OutClass static sNum = " + sNum);
            System.out.println("InClass iNum = " + iNum);
            // System.out.println("InClass static sInNum = " + sInNum);
        }
    }

    public void usingClass() {
        inClass.inTest(); /* 내부 클래스의 메서드를 호출하는 메서드를 선언한다 */
    }
}

public class InnerClazz {
    public static void main(String[] args) {
        /**
         * 내부클래스란, 클래스 내부에 선언한 클래스로 이 클래스를 감싸고있는 외부 클래스와 밀접한 연관이 있는 경우가 많다.
         * 다른 외부 클래스에서 사용할 일이 거의 없는 경우에 내부 클래스로 선언하여 사용한다.
         * 추상 클래스라고도 한다.
         *
         * 내부적으로 사용할 클래스는 private 로 선언하는 것을 권장한다. (외부에서 사용할 수 없게)
         * 외부 클래스가 생성된 후 생성된다.
         */

        OutClass outClass = new OutClass();
        outClass.usingClass();

        // OutClass.InClass inner = new OutClass.InClass();
        // InClass 가 private 선언되면 아래처럼 접근할 수 없다.
        // OutClass.InClass inner = outClass.new InClass();
        // inner.inTest();
    }
}
