package com.spring.batch._11_executionContext;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet2 implements Tasklet {
    /**
     * 비즈니스 로직 구현
     * @param stepContribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("step2 was executed");

        /* ExecutionContext */
        ExecutionContext jobExecutionContext = stepContribution.getStepExecution()
                .getJobExecution().getExecutionContext();

        ExecutionContext stepExecutionContext = stepContribution.getStepExecution()
                .getExecutionContext();

        /* Tasklet1 에서 설정한 ExecutionContext 확인 */
        // JobExecution 은 Step 끼리 공유할 수 있다.
        System.out.println("jobName : " + jobExecutionContext.get("jobName"));
        // StepExecution 은 Step 끼리 공유할 수 없다. Job 끼리 공유 가능하다.
        System.out.println("stepName : " + stepExecutionContext.get("stepName"));

        /* stepName */
        String stepName = chunkContext.getStepContext().getStepExecution()
                .getStepName();

        // step 끼리 공유가 안되므로 null 일 것이다.
        if (stepExecutionContext.get("stepName") == null) {
            stepExecutionContext.put("stepName", stepName);
        }

        return RepeatStatus.FINISHED;
    }
}
