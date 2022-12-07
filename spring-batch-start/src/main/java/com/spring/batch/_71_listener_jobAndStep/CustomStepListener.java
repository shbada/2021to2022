package com.spring.batch._71_listener_jobAndStep;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class CustomStepListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        String stepName = stepExecution.getStepName();

        stepExecution.getExecutionContext().putString("name", "user1");
        System.out.println("stepExecution.getStepName() : " + stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        ExitStatus exitStatus = stepExecution.getExitStatus(); // 종료코드
        BatchStatus status = stepExecution.getStatus(); // 배치 상태

        System.out.println("stepExecution.getStatus() : " + stepExecution.getStatus());

        /**
        동일한 스텝의 getExecutionContext 에서 꺼내오는것.

        step1, step2
        step 은 다르지만 CustomStepListener 가 빈으로 되어있는데, 이게 step1, step2 모두에 등록되어있다.
        만약에 빈이 아닌 new 클래스() 로 등록했다면 step 별로 각각 서로 다른 listener 이 됬겠지만,
        빈으로 설정했기 때문에 step 끼리 데이터를 공유할 수 있다.
         */
        String name = (String) stepExecution.getExecutionContext().get("name");
        System.out.println("name : " + name);

        return ExitStatus.COMPLETED;
    }
}
