package com.algorithm._11_백트래킹;

/**
 * @Date 2022/03/12
 * @URL https://programmers.co.kr/learn/courses/30/lessons/43165
 */
public class P43165_티켓_넘버 {
    static int t;
    static int count;
    static boolean[] visited;

    public static void main(String[] args) {
	    // write your code here
        P43165_티켓_넘버 main = new P43165_티켓_넘버();
        System.out.println(main.solution(new int[]{1,1,1,1,1}, 3));
    }

    public int solution(int[] numbers, int target) {
        int answer = 0;
        t = target;
        visited = new boolean[numbers.length];

        dfs(numbers, 0);

        answer = count;
        return answer;
    }

    public void dfs(int[] numbers, int index) {
        // 탈출 조건
//        if (index == t) {
//            return;
//        }

        // count++
        int sum = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (visited[i]) {
                sum += (numbers[i] * (-1));
            } else {
                sum += (numbers[i]);
            }
        }

        if (sum == t) {
            count++;
        }

        // 반복
        // true false false false false
        // true true false false false
        // true true true false false
        // true true true true false
        // true true true true true
        // false true true true true
        for (int i = index; i < numbers.length; i++) {
            visited[i] = true; // -

            dfs(numbers, i + 1);

            visited[i] = false; // +
        }
    }
}

/**
 * 채택 풀이
 * https://programmers.co.kr/learn/courses/30/lessons/43165/solution_groups?language=java
 */
class Solution {
    public int solution(int[] numbers, int target) {
        int answer = 0;
        answer = dfs(numbers, 0, 0, target);
        return answer;
    }

    int dfs(int[] numbers, int n, int sum, int target) {
        if(n == numbers.length) {
            if(sum == target) {
                return 1;
            }
            return 0;
        }
        return dfs(numbers, n + 1, sum + numbers[n], target) + dfs(numbers, n + 1, sum - numbers[n], target);
    }
}
