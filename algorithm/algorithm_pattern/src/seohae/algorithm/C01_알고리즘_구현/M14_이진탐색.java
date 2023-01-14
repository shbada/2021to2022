package seohae.algorithm.C01_알고리즘_구현;

import java.util.Scanner;

public class M14_이진탐색 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 원소의 개수(n)와 찾고자 하는 값(target)을 입력받기
        int n = sc.nextInt();
        int target = sc.nextInt();

        // 전체 원소 입력받기
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // 이진 탐색 수행 결과 출력
        int result = binarySearch(arr, target, 0, n - 1);

        if (result == -1) {
            System.out.println("원소가 존재하지 않습니다.");
        } else {
            System.out.println(result + 1);
        }
    }

    // 순차 탐색 소스코드 구현
    public static int binarySearch(int[] arr, int target, int start, int end) {
        while (start <= end) {
            // 중간점 찾기
            int mid = (start + end) / 2;

            if (arr[mid] == target) { // 찾은 경우 중간점 인덱스 반환
                return mid;
            } else if (arr[mid] > target) { // 중간점의 값보다 찾고자 하는 값이 작은 경우 왼쪽 확인
                end = mid - 1;
            } else {
                start = mid + 1; // 중간점의 값보다 찾고자 하는 값이 큰 경우 오른쪽 확인
            }
        }
        return -1;
    }
}
