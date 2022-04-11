package com.spring.batch.faultTolerant;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.repeat.exception.SimpleLimitExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class FaultTolerantConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<String, String>chunk(5)
                .reader(new ItemReader<String>() {
                    int i = 0;
                    @Override
                    public String read() {
                        i++;
                        if(i == 1) {
                            throw new IllegalArgumentException("skip");
                        }
                        return i > 3 ? null : "item" + i;
                    }
                })
                .processor((ItemProcessor<String, String>) item -> {
                    throw new IllegalStateException("retry");
//                    return item;
                })
                .writer(items -> System.out.println(items))
                // FaultTolerantStepBuilder 클래스 생성
                // FaultTolerantChunkProvider
                // FaultTolerantChunkProcessor
                .faultTolerant()
                // skip 하면 skipCount + 1
                .skip(IllegalArgumentException.class) // 이 예외 발생시 skip
                .skipLimit(1) // 1번에 걸쳐서 skip
                .retry(IllegalStateException.class) // 이 예외 발생시 retry
                // (item1, item2 까지 시도하고 위 에러가 2번 발생했으므로 탈출
                //  skip 할것인지 체크하는데, 에러가 다르므로 skip 할 수 없으므로 오류를 발생하고 잡 실패)
                .retryLimit(2) // 2번에 걸쳐서 retry
                .build();
    }

    @Bean
    public SimpleLimitExceptionHandler simpleLimitExceptionHandler(){
        return new SimpleLimitExceptionHandler(3);
    }
}