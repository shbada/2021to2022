package org.example.chap02_multiThread._step09_parallel_exam;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/*
 * Below utility searches the given pattern in the file.
 */
public class PatternFinder {

    /*
     * Looks for the given pattern in the file,
     * and returns the list of line numbers
     * in which the pattern is found.
     */
    public List<Integer> find(File file, String pattern) {

        List<Integer> lineNumbers = new ArrayList<Integer>();

        // file 읽기 시작
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            int lineNo = 1;
            String line;

            // file line
            while ( (line = br.readLine()) != null) {

                if (line.contains(pattern)) {
                    // lineNo add
                    lineNumbers.add(lineNo);
                }

                lineNo++;
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        // 1초 지연
        try { Thread.sleep(1000); } catch(Exception e) {}

        return lineNumbers;
    }

}
