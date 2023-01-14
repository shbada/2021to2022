package item03_lamda;

public class StringConcatTest {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = "World";

        StringConcatImpl stringConcat = new StringConcatImpl();
        stringConcat.makeString(s1, s2);

        /** 람다식으로 사용 : StringConcatImpl 을 사용하지않음 */
        StringConcat concat = (param1, param2) -> System.out.println(param1 + ":" + param2);
        concat.makeString(s1, s2);

        /** 익명메서드 사용 */
        StringConcat concat2 = new StringConcat() {
            @Override
            public void makeString(String s1, String s2) {
                System.out.println(s1 + ">"+ s2);
            }
        };

        concat2.makeString(s1, s2);
    }
}
