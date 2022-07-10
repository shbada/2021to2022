package com.project.springbatch._44_parallel;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class ParallelStopWatchJobListener implements JobExecutionListener {
    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long time = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();
        System.out.println("time : " + time);
    }
}
