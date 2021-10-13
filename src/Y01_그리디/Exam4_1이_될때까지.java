package Y01_그리디;

import java.util.Scanner;

public class Exam4_1이_될때까지 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // N, K를 공백을 기준으로 구분하여 입력 받기
        int n = sc.nextInt();
        int k = sc.nextInt(); /* k 로 나누거나 -1 */
        int result = 0;

        while (n > 1) {
            if (n % k == 0) {
                n = n / k;
            } else {
                n = n - 1;
            }
            result++;
        }

        System.out.println(result);
    }

    /* 해설 */
    public static void 해답보기() {
        Scanner sc = new Scanner(System.in);

        // N, K를 공백을 기준으로 구분하여 입력 받기
        int n = sc.nextInt();
        int k = sc.nextInt();
        int result = 0;

        while (true) {
            // N이 K로 나누어 떨어지는 수가 될 때까지만 1씩 빼기
            int target = (n / k) * k; /** n 이 k의 배수가 되도록 */
            result += (n - target);
            n = target;
            // N이 K보다 작을 때 (더 이상 나눌 수 없을 때) 반복문 탈출
            if (n < k) break;
            // K로 나누기
            result += 1;
            n /= k;
        }

        // 마지막으로 남은 수에 대하여 1씩 빼기
        result += (n - 1);
        System.out.println(result);
    }
}
