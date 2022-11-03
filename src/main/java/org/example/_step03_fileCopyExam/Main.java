package org.example._step03_fileCopyExam;

import org.example._step03_fileCopyExam.utils.IOUtils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CopyTask implements Runnable {
    String sourceFile;
    String destFile;

    public CopyTask(String sourceFile, String destFile) {
        this.sourceFile = sourceFile;
        this.destFile = destFile;
    }

    @Override
    public void run() {
        try {
            // Initiate Copy
            IOUtils.copyFile(sourceFile, destFile);
            System.out.println("Copied from - " + sourceFile + " to " + destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        String sourceFile1 = "a.txt";
        String sourceFile2 = "b.txt";

        String destFile1 = "c.txt";
        String destFile2 = "d.txt";

        // 100개 존재하면, 100개의 스레드?
        // 10개의 스레드를 사용하여 이 100개의 파일 복사 작업을 수행하는게 좋겠다.
        // -> ExecutorService

        // 스레드 풀 크기가 5인 스레드 풀 실행자 생성
        ExecutorService executor = Executors.newFixedThreadPool(5);
        executor.execute(new CopyTask(sourceFile1, destFile1));
        executor.execute(new CopyTask(sourceFile2, destFile2));

        // 모든 태스크를 병렬로 수행
        CopyTask copyTask1 = new CopyTask(sourceFile1, destFile1);
        CopyTask copyTask2 = new CopyTask(sourceFile2, destFile2);

        Thread t1 = new Thread(copyTask1);
        Thread t2 = new Thread(copyTask2);

        t1.start();
        t2.start();

        System.out.println("Done");
    }
}
