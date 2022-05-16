package com.spring.batch._11_executionContext;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet4 implements Tasklet {
    /**
     * 비즈니스 로직 구현
     * @param stepContribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("step4 was executed");

        // 재실행했을때 tasklet3 에서 실패했지만 name 은 executionContext 에 저장되어있는데, 정상적으로 출력될까?
        // tasklet3 에서 name 을 저장 후에 오류가 발생했으므로 정상적으로 찍힌다.
        Object name = chunkContext.getStepContext().getStepExecution()
                .getJobExecution().getExecutionContext().get("name");

        System.out.println("name : " + name);

        return RepeatStatus.FINISHED;
    }
}
