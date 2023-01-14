package ModernInJava8.ch3_lamda;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) throws Exception {
        /* 람다 전달
           람다 표현식의 전달 '(BufferedReader br) -> br.readLine() + br.readLine()' 이 인터페이스를 구현하게됨
        */
        String result = processFile((BufferedReader br) -> br.readLine() + br.readLine());
        System.out.println(result);

        String result2 = processFile((BufferedReader br) -> br.readLine());
        System.out.println(result2);
    }

    /**
     * 동작 실행
     * @param p
     * @return
     * @throws Exception
     */
    public static String processFile(BufferedReaderProcessor p) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br); /* 함수형 인터페이스의 함수 호출 */
        }
    }
}
