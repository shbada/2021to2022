package com.algorithm._01_그리디_구현.Dont;

/**
 * @Date 2022/04/21
 * @URL https://programmers.co.kr/learn/courses/30/lessons/12905
 *
 * https://soobarkbar.tistory.com/164
 */
public class P12905_가장_큰_정사각형_찾기 {
    public static void main(String[] args) {
        // write your code here
        P12905_가장_큰_정사각형_찾기 main = new P12905_가장_큰_정사각형_찾기();
        main.solution(new int[][]{{0,1,1,1},{1,1,1,1},{1,1,1,1},{0,0,1,0}});
    }

    public int solution(int [][] board) {
        int answer = 0;

        int[][] newBoard = new int[board.length + 1][board[0].length + 1];

        // 새로운 배열 생성
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                newBoard[i + 1][j + 1] = board[i][j];
            }
        }

        // 만들 수 있는 정사각형 검사
        for(int i = 1; i < newBoard.length; i++) {
            for (int j = 1; j < newBoard[i].length; j++) {
                if (newBoard[i][j] != 0) {
                    newBoard[i][j] = Math.min(Math.min(newBoard[i - 1][j], newBoard[i][j]), newBoard[i - 1][j - 1]);
                    answer = Math.max(answer, newBoard[i][j]);
                }
            }
        }

        return answer * answer;
    }
}
