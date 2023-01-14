package item01_innerClass;

class OutClass2 {
    private int num = 10;
    private static int sNum = 20;
    private InClass2 inClass;

    public OutClass2() { /* OutClass 생성시 아래 내부클래스를 생성 */
        inClass = new InClass2();
    }

    private class InClass2 {
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

    static class InStaticClass {
        int iNum = 100;
        static int sInNum = 200;

        /**
         * static 메서드이므로 static 변수가 아닌 변수의 사용은 에러가 발생한다.
         */
        void inTest() {
            System.out.println("OutClass static sNum = " + sNum); /* 외부 클래스의 static 변수 사용 가능 */
            System.out.println("InClass iNum = " + iNum);
            System.out.println("InClass static sInNum = " + sInNum);
        }

        static void inStaticTest() {
            System.out.println("OutClass static sNum = " + sNum); /* 외부 클래스의 static 변수 사용 가능 */
            // System.out.println("InClass iNum = " + iNum); /* 내부 클래스의 static 변수가 아닌 변수는 사용 못함 */
            System.out.println("InClass static sInNum = " + sInNum);
        }
    }
}

public class InnerStaticClazz {
    public static void main(String[] args) {
        /**
         * 정적 내부 클래스
         * 외부 클래스 생성과 무관하게 사용할 수 있다.
         * 정적 변수, 정적 메서드를 사용한다.
         */
        OutClass2.InStaticClass sInClass = new OutClass2.InStaticClass();
        sInClass.inTest();

        OutClass2.InStaticClass.inStaticTest();
    }
}
