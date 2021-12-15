package com.batch.order.order;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;
import org.springframework.util.StringUtils;

/**
 * JobExecutionDecider 구현체
 *
 * 해당 decide 값에 따라서 배치 실행 여부를 선택할 수 있다.
 */
public class JobParametersDecide implements JobExecutionDecider {
    /* status 를 커스텀하게 재정의 */
    public static final FlowExecutionStatus CONTINUE = new FlowExecutionStatus("CONTINUE");

    /* 파라미터 존재 여부를 판단하기 위한 값 (파라미터 key value) */
    private final String key;

    public JobParametersDecide(String key) {
        this.key = key;
    }

    @Override
    public FlowExecutionStatus decide(JobExecution jobExecution, StepExecution stepExecution) {
        String value = jobExecution.getJobParameters().getString(key);

        /* 파라미터 값이 비어있다면 종료 */
        if (StringUtils.isEmpty(value)) {
            return FlowExecutionStatus.COMPLETED;
        }

        /* 그 외는 계속 실행 */
        return CONTINUE;
    }
}
