package com.spring.batch._31_custom_exitStatus;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class PassCheckingListener implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        // exit_status 셋팅
        String exitCode = stepExecution.getExitStatus().getExitCode();

        // FAILED 가 아니면
        if (!exitCode.equals(ExitStatus.FAILED.getExitCode())) {
            return new ExitStatus("PASS"); // 사용자 정의 코드 반환
        }
        return null;
    }
}
