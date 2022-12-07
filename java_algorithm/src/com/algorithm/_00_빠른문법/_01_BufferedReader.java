package com.algorithm._00_빠른문법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class _01_BufferedReader {
    void input1() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /*
            1 2 3... 입력
        */
        int n = Integer.parseInt(br.readLine()); // read, readLine 밖에 없으므로 정수는 파싱 필요

        for (int i = 0; i < n; i++) {
            String[] text = br.readLine().split(" "); // 1, 2, 3..
        }
    }

    void input2() throws IOException {
        /*
          n = 3
        */
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine()); // 3

        /**
         a
         b
         c 입력
         */
        for (int i = 0; i < n; i++) {
            String str = bf.readLine(); // 한줄에 한단어
        }
    }
}
