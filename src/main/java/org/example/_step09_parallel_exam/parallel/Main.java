package org.example._step09_parallel_exam.parallel;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.example._step09_parallel_exam.PatternFinder;

/**
 * 1스레드: a.txt : 1초
 * 2스레드: b.txt : 1초
 * 3스레드: c.txt : 1초
 * 동시에 수행
 *
 * = 총 1초정도
 */
public class Main {

    public static void main(String[] args) throws Exception {

        String pattern = "public";

        File dir = new File("./src/sample");
        File [] files = dir.listFiles();

        PatternFinder finder = new PatternFinder();

        // Fixed thread pool of size 3.
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // key : fileName, value : future
        Map<String, Object> resultMap = new HashMap<String,Object>();

        long startTime = System.currentTimeMillis();

        for (File file : files) {

            // Callable Task
            Future<List<Integer>> future =
                    executor.submit(
                            new Callable<List<Integer>>() {
                                public List<Integer> call() {
                                    List<Integer> lineNumbers = finder.find(file, pattern);
                                    return lineNumbers;
                                }
                            });

            resultMap.put(file.getName(), future);
        }

        // Wait for the requests to complete.
        waitForAll( resultMap );

        // Display the result.
        for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
            System.out.println(
                    pattern + " found at - " + entry.getValue() +
                            " in file " + entry.getKey());
        }


        System.out.println(
                " Time taken for search - "
                        + (System.currentTimeMillis() - startTime));

    }

    private static void waitForAll(Map<String, Object> resultMap)
            throws Exception {

        Set<String> keys =  resultMap.keySet();

        for (String key : keys) {
            Future<List<Integer>> future =
                    (Future<List<Integer>>) resultMap.get(key);

            // CPU를 지정된 기간 동안 유휴 상태로 유지
            // CPU를 대기하는 다른 스레드에 컨트롤을 전달
            while (! future.isDone()) {
                Thread.yield();
            }

            // 획득한 결과값으로 change
            resultMap.put(key, future.get());
        }

    }
}

