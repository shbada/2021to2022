package com.app.springbatch.batch.job.apiJob;

import com.app.springbatch.batch.listener.JobListener;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ApiJobChildConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    /* 내부적으로 job을 실행하는데,
       멀티 스레드 환경에서 API 통신을 실행하려고 한다.
       DB 데이터(제품 정보)를 읽고 API 호출을 하는 통신을 제품별로 각각 스레드를 생성하여 처리하기 위함
       파티셔닝 기능을 사용할건데, 여기서 master Step, slave Step 설정을 할것임
ㅁ    */
    private final Step apiMasterStep;
    private final JobLauncher jobLauncher;

    @Bean
    public Step jobStep() {
        return stepBuilderFactory.get("jobStep")
                .job(childJob())
                .launcher(jobLauncher)
                .build();
    }

    @Bean
    public Job childJob() {
        return jobBuilderFactory.get("childJob")
                .start(apiMasterStep)
                .build();
    }
}
