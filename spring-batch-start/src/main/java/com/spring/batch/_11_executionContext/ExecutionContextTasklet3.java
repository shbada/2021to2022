package com.spring.batch._11_executionContext;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

@Component
public class ExecutionContextTasklet3 implements Tasklet {
    /**
     * 비즈니스 로직 구현
     * @param stepContribution
     * @param chunkContext
     * @return
     * @throws Exception
     */
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("step3 was executed");

        // 최초일 경우, 저장한적 없으므로 null
        Object name = chunkContext.getStepContext().getStepExecution()
                .getJobExecution().getExecutionContext().get("name");

        /* 에러 고의로 발생시켜서 tasklet4 에서 재실행했을때 name 을 가져올 수 있는지 테스트 해보자.
        * step1, step2 에서 저장된 데이터는 들어있고, (완료)
        * step3 부터 실행이 될 것이다.
        * 재시작을 하면 아래 name 도 null 이 아니다. (user1이 저장되어있다)
        * 따라서 재시작시, 아래 if 문을 타지 않기 때문에 에러가 발생하지 않는다.
        *  */
        if (name == null) {
            chunkContext.getStepContext().getStepExecution()
                    .getJobExecution().getExecutionContext().put("name", "user1");
            throw new RuntimeException("step2 was failed"); // 예외 발생시키기
        }

        return RepeatStatus.FINISHED;
    }
}
