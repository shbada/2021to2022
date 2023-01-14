package com.batch.order.batch;

import com.batch.order.entity.User;
import com.batch.order.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import java.time.LocalDate;
import java.util.Collection;

@RequiredArgsConstructor
@Slf4j
public class LevelUpJobExecutionListener implements JobExecutionListener {
    private final UserRepository userRepository;

    @Override
    public void beforeJob(JobExecution jobExecution) {

    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        Collection<User> users = userRepository.findAllByUpdatedDate(LocalDate.now());

        /* 이 잡이 실행된 시간 측정 */
        long time = jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();

        log.info("Batch Start...");
        log.info("------------");
        log.info("총 데이터 처리 {}건, 처리시간 {}millis", users.size(), time);
    }
}
