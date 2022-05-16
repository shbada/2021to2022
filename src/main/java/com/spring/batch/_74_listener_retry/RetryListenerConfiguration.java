package com.spring.batch._74_listener_retry;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class RetryListenerConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final CustomRetryListener customRetryListener;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Integer, String>chunk(10)
                .reader(listItemReader())
                .processor(new CustomItemProcessor())
                .writer(new CustomItemWriter())
                .faultTolerant()
                .retry(CustomRetryException.class)
                .retryLimit(2)
                /**
                 * RetryTemplate -> doExecute() 수행
                 * retry 전에 doOpenInterceptors() 를 통해서 retry 실행이 가능한지를 체크함
                 * 여기서 listener.open()을 호출하는 것
                 *
                 * 1) listener > open() 호출 : 반환값 result(boolean)
                 * -> true 면 retryCallback 수행됨
                 * 2) FaultTolerantChunkProcessor 에서 itemProcessor, itemWriter 이 수행됨
                 * -> [retryCallback] 여기서 retryTemplate 의 doWithRetry 로 들어왔고,
                 * 3) listener > close() 호출
                 * -> open-close 는 재시도를 할때마다 실행이 된다.
                 *    그래서, open - close 사이에는 itemProcessor, itemWriter 이 수행되겠다.
                 *
                 * [itemProcessor]
                 * 재시도 수행
                 * 1) open()
                 * 2) 에러 발생시,
                 * doOnErrorInterceptors()
                 * listener -> onError() 호출
                 * 3) close()
                 *
                 * 오류가 발생하던, 하지않던 open-close 는 무조건 발생하고,
                 * 오류가 발생한 경우에만 onError()가 발생한다.
                 *
                 * 프로세스가 끝나면,
                 *
                 * [ItemWriter] 로 왔다.
                 * 에러 발생
                 * 1) open()
                 * 2) onError()
                 * 3) close()
                 *
                 */
                .listener(customRetryListener)

                .build();
    }

    @Bean
    public ItemReader<Integer> listItemReader() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
//        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        return new LinkedListItemReader<>(list);
    }
}
