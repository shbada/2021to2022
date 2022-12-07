package com.algorithm._11_백트래킹;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Date 2022/04/20
 * @URL https://leetcode.com/problems/permutations-ii/
 */
public class L46_Permutations2_Set {
    Set<List<Integer>> resultSet = new HashSet<>();
    List<Integer> paramList = new ArrayList<>();
    int[] map;
    int n;
    boolean[] visited;

    public static void main(String[] args) {
        L46_Permutations2_Set solution = new L46_Permutations2_Set();

        int[] dx = {1,1,2};
        //int[] dx = {0,1};

        System.out.println(solution.permute(dx));
    }

    public List<List<Integer>> permute(int[] nums) {
        this.map = nums;
        n = nums.length;

        visited = new boolean[n];

        dfs(paramList);

//        List<List<Integer>> resultList = new ArrayList<>();
//        resultList.addAll(resultSet);
//        return resultList;
        return new ArrayList<>(resultSet);
    }

    public void dfs(List<Integer> paramList) {
        // 탈출 조건
        if (paramList.size() == n) {
            resultSet.add(new ArrayList<>(paramList));
            return;
        }

        // 구현
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                paramList.add(map[i]);

                dfs(paramList);

                visited[i] = false;
                paramList.remove(paramList.size() - 1);
            }
        }
    }
}
