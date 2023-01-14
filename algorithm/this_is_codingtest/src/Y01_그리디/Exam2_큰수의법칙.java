package Y01_그리디;

import java.util.Arrays;
import java.util.Scanner;

public class Exam2_큰수의법칙 {
    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 5, 4, 6};

        int m = 8; // 개수 제한
        int k = 3; // 반복 개수 제한

        // 정렬
        Arrays.sort(arr); // 입력 받은 수들 정렬하기

        // 가장 큰 수 추출
        int max = arr[arr.length - 1];
        int secondMax = arr[arr.length - 2];

        int result = 0;

        int copyK = k;
        for (int i = 0; i < m; i++) {
            if (copyK == 0) {
                result += secondMax;
                copyK = k;
            } else {
                result += max;
                copyK = copyK - 1;
            }
        }

        System.out.println(result);
    }

    /* 해설 */
    public static void 해답보기() {
        Scanner sc = new Scanner(System.in);

        // N, M, K를 공백을 기준으로 구분하여 입력 받기
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();

        // N개의 수를 공백을 기준으로 구분하여 입력 받기
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr); // 입력 받은 수들 정렬하기
        int first = arr[n - 1]; // 가장 큰 수
        int second = arr[n - 2]; // 두 번째로 큰 수

        // 가장 큰 수가 더해지는 횟수 계산
        int cnt = (m / (k + 1)) * k;
        cnt += m % (k + 1);

        int result = 0;
        result += cnt * first; // 가장 큰 수 더하기
        result += (m - cnt) * second; // 두 번째로 큰 수 더하기

        System.out.println(result);
    }
}
