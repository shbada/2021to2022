package com.spring.batch._멀티_스레드_프로세싱.async;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class StopWatchJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long time = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();
        System.out.println("time : " + time);
    }
}
