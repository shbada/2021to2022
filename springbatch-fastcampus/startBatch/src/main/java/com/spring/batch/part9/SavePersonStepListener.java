package com.spring.batch.part9;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.annotation.BeforeStep;

@Slf4j
public class SavePersonStepListener {
    /**
     * 인터페이스로 Listener 생성
     */
    public static class SavePersonStepExecutionListener implements StepExecutionListener {
        @Override
        public void beforeStep(StepExecution stepExecution) {
            log.info("beforeStep");
        }

        @Override
        public ExitStatus afterStep(StepExecution stepExecution) {
            log.info("afterStep : " + stepExecution.getWriteCount());

            return stepExecution.getExitStatus();
        }
    }

    /**
     * 어노테이션으로 Listener 생성
     */
    public static class SavePersonAnnotationStepExecutionListener {
        @BeforeStep
        public void beforeJob(StepExecution stepExecution) {
            log.info("annotation beforeStep");
        }

        @AfterStep
        public ExitStatus afterJob(StepExecution stepExecution) {
            log.info("annotation afterStep : " + stepExecution.getWriteCount());

//            if (stepExecution.getWriteCount() == 0) {
//                return ExitStatus.FAILED;
//            }

            return stepExecution.getExitStatus();
        }
    }
}
