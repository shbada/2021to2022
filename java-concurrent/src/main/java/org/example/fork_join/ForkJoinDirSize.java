package org.example.fork_join;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * 특정 디렉토리에 포함되어 있는 모든 파일의 크기를 구하는 프로그램
 */
public class ForkJoinDirSize extends RecursiveTask<Long> {
    private final Path path;

    public ForkJoinDirSize(Path path) {
        this.path = path;
    }

    @Override
    protected Long compute() {
        long fileSize = 0;

        // 디렉토리 일경우 SUBTASK 생성하고 호출
        if (Files.isDirectory(path)) {
            try {
                List<Path> fileList = Files.list(path).collect(Collectors.toList());
                List<ForkJoinDirSize> subTaskList = new ArrayList<>();

                for (Path file : fileList) {
                    /* 디렉터리 하위를 재귀처리 */
                    ForkJoinDirSize subTask = new ForkJoinDirSize(file);
                    subTask.fork(); // 백그라운드에서 멀티 스레드 형태로 실행시키겠다는 의미
                    subTaskList.add(subTask); // 서브 태스크를 다시 List에 추가하여, 실행된 결과를 리턴받는다.
                }

                Long subSize = 0L;

                for (ForkJoinDirSize subTask : subTaskList) {
                    // compute() 리턴 값을 얻는다.
                    subSize += subTask.join(); // 조인 작업은 포크된 모든 작업이 종료될 때까지 기다린다.
                }

                return subSize;
            } catch(IOException e) {
                System.out.println("Error : " + path);
            }
        } else { // 파일일 경우 크기 리턴
            try {
                fileSize = Files.size(path);
            } catch(IOException e) {
                System.out.println("Error : " + path);
            }
        }

        return fileSize;
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Path rootPath = Paths.get("c:\\Program Files");

        /* 포크/조인 프레임워크로 개발된 코드를 실행하기 위해 ForkJoinPool을 이용한다. */
        ForkJoinPool pool = new ForkJoinPool(); // 스레드풀의 크기를 지정하지 않으면 4개의 스레드 풀이 생성된다.
        System.out.printf("병렬 처리 크기 : %s\n", pool.getParallelism());
        System.out.printf("합계 : %s\n", pool.invoke(new ForkJoinDirSize(rootPath)));

        long endTime = System.currentTimeMillis();

        System.out.printf("처리 시간 : " + (endTime - startTime));
    }
}