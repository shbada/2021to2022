package com.app.springbatch.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class ApiSchJob extends QuartzJobBean {

    private final Job apiJob;

    private final JobLauncher jobLauncher;

    /**
     * trigger 시간 주기가 되면 해당 메서드가 호출된다.
     * 그래서 이 메서드에서 배치를 수행시키면 된다.
     * @param context
     * @throws JobExecutionException
     */
    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("id", new Date().getTime())
                .toJobParameters();
        jobLauncher.run(apiJob, jobParameters);
    }
}

