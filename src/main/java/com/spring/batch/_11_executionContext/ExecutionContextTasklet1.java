package com.spring.batch._11_executionContext;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet1 implements Tasklet {
    /**
     * 비즈니스 로직 구현
     * @param stepContribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("step1 was executed");

        /* ExecutionContext */
        ExecutionContext jobExecutionContext = stepContribution.getStepExecution()
                .getJobExecution().getExecutionContext();

        ExecutionContext stepExecutionContext = stepContribution.getStepExecution()
                .getExecutionContext();

        /* jobName */
        String jobName = chunkContext.getStepContext().getStepExecution()
                .getJobExecution().getJobInstance().getJobName();

        /* stepName */
        String stepName = chunkContext.getStepContext().getStepExecution()
                .getStepName();

        // jobExecutionContext 안에 jobName 을 key 로 하는 데이터를 저장한 경우가 없을때
        // job 최초 실행시 put 이 될것이다.
        if (jobExecutionContext.get("jobName") == null) {
            jobExecutionContext.put("jobName", jobName);
        }

        // job 최초 실행시 put 이 될것이다.
        if (stepExecutionContext.get("stepName") == null) {
            stepExecutionContext.put("stepName", stepName);
        }

        System.out.println("jobName = " +  jobExecutionContext.get("jobName"));
        System.out.println("stepName = " +  stepExecutionContext.get("stepName"));

        return RepeatStatus.FINISHED;
    }
}
