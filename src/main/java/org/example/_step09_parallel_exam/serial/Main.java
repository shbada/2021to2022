package org.example._step09_parallel_exam.serial;

import org.example._step09_parallel_exam.PatternFinder;

import java.io.File;
import java.util.List;

/**
 * a.txt : 1초
 * b.txt : 1초
 * c.txt : 1초
 * 총 3초
 */
public class Main {

    public static void main(String[] args) throws Exception {

        // pattern to search
        String pattern = "public";

        // Directory or folder to search
        File dir = new File("./src/sample");

        // list all the files present in the folder.
        File [] files = dir.listFiles();

        PatternFinder finder = new PatternFinder();

        long startTime = System.currentTimeMillis();

        // file 개수만큼 수행
        // 각 파일을 순차적 순서로 스캔
        for (File file : files) {

            List<Integer> lineNumbers = finder.find(file, pattern);

            // 출력
            if (!lineNumbers.isEmpty()) {
                System.out.println(
                        pattern + "; found at " + lineNumbers +
                                " in the file - " + file.getName());
            }

        }

        System.out.println(
                " Time taken for search - " + (System.currentTimeMillis() - startTime));

    }
}
