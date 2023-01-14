package com.spring.batch._71_listener_jobAndStep;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.BeforeJob;

public class CustomAnnotationJobListener {

    @BeforeJob
    public void beforeJob(JobExecution JobExecution) { // 어노테이션일땐 메서드명 자유롭게 작성 가능
        System.out.println("JobExecution.getJobName() : " + JobExecution.getJobInstance().getJobName());
    }

    @AfterJob
    public void afterJob(JobExecution JobExecution) {
        System.out.println("JobExecution.getStatus() : " + JobExecution.getStatus());
    }
}

