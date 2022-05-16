package com.spring.batch._12_jobRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JobRepositoryListener implements JobExecutionListener {
    private final JobRepository jobRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();

        // 실제로 저장된 데이터로 셋팅
        // BATH_JOB_EXECUTION_PARAMS : JOB_EXECUTION_ID
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("requestDate", "20210102").toJobParameters();

        // jobParameters 는 DB 에 저장되어있는 데이터를 가져온다.
        JobExecution lastJobExecution = jobRepository.getLastJobExecution(jobName, jobParameters);

        if (lastJobExecution != null) {
            for (StepExecution stepExecution : lastJobExecution.getStepExecutions()) {
                BatchStatus status = stepExecution.getStatus();
                ExitStatus exitStatus = stepExecution.getExitStatus();

                log.info("BatchStatus : {}, ExitStatus : {}", status, exitStatus);

                String stepName = stepExecution.getStepName();

                log.info("stepName : {}", stepName);
            }
        }
    }
}
