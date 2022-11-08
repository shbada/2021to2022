package org.example.chap02_multiThread._step03_fileCopyExam.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IOUtils {
    public static void copy(InputStream src, OutputStream dest) throws IOException {
        int value;

        while ((value = src.read()) != -1) {
            dest.write(value);
        }
    }

    public static void copyFile(String src, String dest) throws IOException {
        FileInputStream fin = new FileInputStream(src);
        FileOutputStream fout = new FileOutputStream(dest);

        IOUtils.copy(fin, fout);

        System.out.println("Done");

        fin.close();
        fout.close();
    }
}
