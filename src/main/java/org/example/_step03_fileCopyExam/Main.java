package org.example._step03_fileCopyExam;

import org.example._step03_fileCopyExam.utils.IOUtils;

import java.io.IOException;

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

        CopyTask copyTask1 = new CopyTask(sourceFile1, destFile1);
        CopyTask copyTask2 = new CopyTask(sourceFile2, destFile2);

        Thread t1 = new Thread(copyTask1);
        Thread t2 = new Thread(copyTask2);

        t1.start();
        t2.start();

        System.out.println("Done");
    }
}
