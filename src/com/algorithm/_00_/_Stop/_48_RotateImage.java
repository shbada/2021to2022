package com.algorithm._00_._Stop;

import java.util.Arrays;

/**
 * @Date 2022/03/09
 * @URL https://leetcode.com/problems/rotate-image/
 */
public class _48_RotateImage {
    public static void main(String[] args) {
        _48_RotateImage solution = new _48_RotateImage();
        int[][] a = new int[][] { {1,2,3}, {4,5,6}, {7,8,9}};

        solution.rotate(a);
        System.out.println(Arrays.deepToString(a));
    }

    /**
     1 2 3
     4 5 6
     7 8 9

     (0,0) (0,1) (0,2)  i = 0 j++
     (1,0) (1,1) (1,2)  i = 1 j++
     (2,0) (2,1) (2,2)  i = 2 j++

     (2,0) (1,0) (0,0)  j = 0 i--
     (2,1) (1,1) (0,1)  j = 1 i--
     (2,2) (1,2) (0,2)  j = 2 i--
     */
//    public int[][] rotate(int[][] matrix) {
//        /** TODO 또다른 2차원 배열 생성을 하면 안됨 */
//        int[][] result = new int[matrix.length][matrix[0].length];
//
//        for (int j = 0; j < matrix[0].length; j++) { // j = 0, 1, 2
//            for (int i = matrix.length - 1; i >= 0; i--) { // i = 2, 1, 0
//                result[j][matrix.length - (i + 1)] = matrix[i][j];
//            }
//        }
//
//        return result;
//    }

    /**
     1 2 3
     4 5 6
     7 8 9

     (0,0) (0,1) (0,2)
     (1,0) (1,1) (1,2)
     (2,0) (2,1) (2,2)

     (2,0) (1,0) (0,0)
     (2,1) (1,1) (0,1)
     (2,2) (1,2) (0,2)


     */
    public void rotate(int[][] M) {
        for (int i = 0; i < (M.length+1)/2; i++) {
            for (int j = 0; j < M.length/2; j++) {
                int tmp = M[i][j];
                M[i][j] = M[M.length-j-1][i];
                M[M.length-j-1][i] = M[M.length-i-1][M.length-j-1];
                M[M.length-i-1][M.length-j-1] = M[j][M.length-i-1];
                M[j][M.length-i-1] = tmp;
            }
        }
    }
}
