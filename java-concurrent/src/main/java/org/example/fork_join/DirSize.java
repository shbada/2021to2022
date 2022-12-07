package org.example.fork_join;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 전통적인 방식 - 재귀
 * 포크/조인 프레임워크가 아닌 기존의 순차 처리에 재귀 호출 방식으로 크기를 구해보자.
 */
public class DirSize {
    protected Long compute(Path path) {
        long fileSize = 0;

        try {
            List<Path> fileList = Files.list(path).collect(Collectors.toList());

            for(Path file : fileList) {
                if(Files.isDirectory(file)) {
                    fileSize += compute(file);
                }
                else {
                    fileSize += Files.size(file);
                }
            }
        }
        catch(IOException e) {
            System.out.println("Error : " + path);
        }
        return fileSize;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Path rootPath = Paths.get("c:\\Program Files");

        DirSize dirSize = new DirSize();
        System.out.printf("합계 : %s\n", dirSize.compute(rootPath));

        long endTime = System.currentTimeMillis();

        System.out.printf("처리 시간 : " + (endTime - startTime));
    }
}
