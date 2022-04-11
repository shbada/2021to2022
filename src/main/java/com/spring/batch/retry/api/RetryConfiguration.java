package com.spring.batch.retry.api;

import com.spring.batch.retry.NoRetryException;
import com.spring.batch.retry.RetryableException;
import com.spring.batch.retry.template.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.policy.SimpleRetryPolicy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RequiredArgsConstructor
@Configuration
public class RetryConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchJob1")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<String, Customer>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .faultTolerant()
                /* skip 설정 추가 - 이유는 RetryItemProcessor.java 주석 참고 */
                .skip(RetryableException.class)
                .skipLimit(2)
                /*
                FaultTolerantStepBuilder > RetryCallback, RecoveryCallback 인자로 batchRetryTemplate.execute 메서드를 호출
                retryCallback 안에서 ItemProcessor, ItemWriter 이 실행된다고 볼 수 있다.
                for 구문을 돌면서, item Chunk size 만큼 item 이 수행이 될텐데 item 1개가 수행될때마다 batchRetryTemplate 이 수행이 된다.
                그리고 새로운 retryCallback, recoveryCallback 이 생성되어,
                각 아이템별로 각 retryCallback, recoveryCallback 을 가지고있다. (공유하지 않는다.)

                BatchRetryTemplate.execute() -> RetryTemplate.execute()
                RetryTemplate 의 doExecute() 메서드 로직이 핵심적인 로직이다.

                RetryContext 안에 Retry 기능을 동작 시키면서 저장해야할 상태 정보를 가지고있다.
                재시도의 여부도 위 RetryContext 로 판단한다.

                SimpleRetryPolicy.java > maxAttempts (retry 최대 시도 가능 횟수)
                RetryContextSupport.java 의 count 가 retry 가 될때마다 count +1 이 수행되는 곳이다.
                위 정보들이 RetryContext 에 저장된다.

                RetryContext 는 key 를 가지고있어서 key 를 사용하여 데이터를 꺼내온다.

                다시 RetryTemplate 로 돌아오자.
                doOpenInterceptor() : retryListener 을 호출시켜주는 역할을 한다.

                backOffPolicy : 재시도를 할때 바로 재시도를 하지 않고 약간의 대기 시간을 갖는 역할을 한다.

                while 구문
                - retryTemplate 이 반복적으로 재시도를 하는 구문이다.

                아무 에러가 발생하지 않은 첫 시도시에는 무조건 while 문이 한번 수행된다.
                처음에는 무조건 통과되어야 하기 때문이다.
                그 이후부터는 retry 조건이 체크되어 수행된다.

                retryCallback.doWithRetry() : processor, writer 수행시킨다.

                더이상 재시도를 발생할 수 없는 조건 만족시,
                그때 이제 recoveryCallback 이 수행된다.

                기본 제공 recoveryCallback
                skip 도 함께 설정되면 recovery 에서 에러난 데이터를 skip 시켜서 배치를 성공되게 할 수 있다.
                만약 skip 설정이 없으면 에러를 뱉는다.
                 */
                .retry(RetryableException.class) // 이 에러 발생시 retry 기능 동작하도록
                .noRetry(NoRetryException.class) // retry 예외 에러 설정
                //.retryPolicy(limitCheckingItemSkipPolicy()) // policy 직접 만들어서 위를 대체하여 설정할 수 있다.
                .retryLimit(2) // 재시도 가능 횟수
                .build();
    }

    @Bean
    public SimpleRetryPolicy limitCheckingItemSkipPolicy() {
        Map<Class<? extends Throwable>, Boolean> exceptionClass = new HashMap<>();
        exceptionClass.put(RetryableException.class, true);
        SimpleRetryPolicy simpleRetryPolicy = new SimpleRetryPolicy(2, exceptionClass);
        return simpleRetryPolicy;
    }

    @Bean
    public ListItemReader<String> reader() {
        List<String> items = new ArrayList<>();

        for(int i = 0; i < 30; i++) {
            items.add(String.valueOf(i));
        }

        return new ListItemReader<>(items);
    }

    @Bean
    public ItemProcessor processor() {
        RetryItemProcessor processor = new RetryItemProcessor();
        return processor;
    }

    @Bean
    public ItemWriter writer() {
        RetryItemWriter writer = new RetryItemWriter();
        return writer;
    }
}