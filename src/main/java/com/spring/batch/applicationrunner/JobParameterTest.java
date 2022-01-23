package com.spring.batch.applicationrunner;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component // bean
@RequiredArgsConstructor
public class JobParameterTest implements ApplicationRunner {
    /* 잡을 실행시키는, 스프링부트가 초기화될때 이미 빈으로 생성되어있어서 주입이 가능하다. */
    private final JobLauncher jobLauncher;

    /* xxxConfiguration 안의 Job 이 빈이므로 주입이 가능하다. */
    private final Job job;

    /**
     * Program argument : name=user1 seq(long)=2L date(date)=2021/01/01 double(double)=16.5
     * 이렇게도 가능하다.
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("name", "seohae")
                .addLong("seq", 1L)
                .addDate("date", new Date())
                .addDouble("double", 16.5)
                .toJobParameters();

        jobLauncher.run(job, jobParameters);
    }
}
