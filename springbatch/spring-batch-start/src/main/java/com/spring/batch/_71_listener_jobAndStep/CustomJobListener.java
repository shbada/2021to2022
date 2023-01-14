package com.spring.batch._71_listener_jobAndStep;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CustomJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution JobExecution) {
        System.out.println("JobExecution.getJobName() : " + JobExecution.getJobInstance().getJobName());
    }

    @Override
    public void afterJob(JobExecution JobExecution) {
        System.out.println("JobExecution.getStatus() : " + JobExecution.getStatus());
    }
}

