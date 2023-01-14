package com.spring.batch._13_jobLauncher.web;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequiredArgsConstructor
public class JobLauncherController {
    private final Job job;
    private final JobLauncher jobLauncher; // proxy 객체

    /* SimpleJobLauncher 는 빈 등록이 불가능
     * BasicBatchConfigurer 를 주입해야한다.
     */
    private final BasicBatchConfigurer basicBatchConfigurer;

    /**
     * 동기 배치 수행
     * @param member
     * @return
     * @throws JobInstanceAlreadyCompleteException
     * @throws JobExecutionAlreadyRunningException
     * @throws JobParametersInvalidException
     * @throws JobRestartException
     */
    @PostMapping("/batch")
    public String launch(@RequestBody Member member) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("id", member.getId())
                .addDate("date", new Date())
                .toJobParameters();

        /* batch 수행 */
        jobLauncher.run(job, jobParameters);

        // job 실행 시간이 3초일때, 3초가 지나야 아래 값이 리턴된다.
        return "batch completed";
    }

    /**
     * 비동기는 직접 SimpleJobLauncher 의 setTaskExecutor 을 설정해줘야한다.
     * SimpleJobLauncher 는 빈 등록이 불가능
     * BasicBatchConfigurer 를 주입해야한다.
     */

    /**
     * 비동기 배치 수행
     * @param member
     * @return
     * @throws JobInstanceAlreadyCompleteException
     * @throws JobExecutionAlreadyRunningException
     * @throws JobParametersInvalidException
     * @throws JobRestartException
     */
    @PostMapping("/async/batch")
    public String asyncLaunch(@RequestBody Member member) throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("id", member.getId())
                .addDate("date", new Date())
                .toJobParameters();

        /* 비동기로 수행 */
        SimpleJobLauncher jobLauncher = (SimpleJobLauncher) basicBatchConfigurer.getJobLauncher();
        jobLauncher.setTaskExecutor(new SimpleAsyncTaskExecutor());

        /*
          만약, 위 주입에서 private final JobLauncher simpleJobLauncher; 로 할 경우
          - JobLauncher 을 생성할 경우는 프록시 객체로 생성됨
          - simpleJobLauncher : 실제 객체가 아니고, 타입을 상속해서 만들어진 proxy 객체이다.
         */
        // SimpleJobLauncher jobLauncher1 = (SimpleJobLauncher) simpleJobLauncher;

        /* batch 수행 */
        jobLauncher.run(job, jobParameters);

        // // job 실행 시간이 3초일때, 일단 결과가 보여지고, 실제로 내부적으로 3초동안 배치가 실행중이다.
        return "batch completed";
    }
}
