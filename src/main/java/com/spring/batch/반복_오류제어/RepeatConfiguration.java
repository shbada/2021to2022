package com.spring.batch.반복_오류제어;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.*;
import org.springframework.batch.repeat.RepeatCallback;
import org.springframework.batch.repeat.RepeatContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.batch.repeat.exception.SimpleLimitExceptionHandler;
import org.springframework.batch.repeat.support.RepeatTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class RepeatConfiguration {

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
                    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
                        i++;
                        return i > 3 ? null : "item" + i;
                    }
                })
                .processor(new ItemProcessor<String, String>() {

                    RepeatTemplate template = new RepeatTemplate();

                    @Override
                    public String process(String item) throws Exception {

                        /*
                           [SimpleCompletionPolicy]
                           RepeatTemplate > isComplete() 의 결과값으로 반복 여부 확인
                           반복할때마다 1씩 증가하는 count를 SimpleCompletionPolicy 가 가지고있다.
                           chunkSize 보다 크면 반복 종료

                         */

                        // 반복할 때마다 count 변수의 값을 1씩 증가
                        // count 값이 chunkSize 값보다 크거나 같을 때 반복문 종료
//                        template.setCompletionPolicy(new SimpleCompletionPolicy(2));

                        /*
                           [TimeoutTerminationPolicy]
                           RepeatTemplate > isComplete() 의 결과값으로 반복 여부 확인
                           계속 반복하다가 3초가 돌면 완료.(아이템 개수 만큼 수행되겠다 -- 3초 - 3초 -  3초)

                         */

                        // 소요된 시간이 설정된 시간보다 클 경우 반복문 종료
//                        template.setCompletionPolicy(new TimeoutTerminationPolicy(3000));

                        // 여러 유형의 CompletionPolicy 를 복합적으로 처리함
                        // 여러 개 중에 먼저 조건이 부합하는 CompletionPolicy 에 따라 반복문이 종료됨
//                        CompositeCompletionPolicy completionPolicy = new CompositeCompletionPolicy();
//                        CompletionPolicy[] completionPolicies = new CompletionPolicy[]{new TimeoutTerminationPolicy(3000),new SimpleCompletionPolicy(2)};
//                        completionPolicy.setPolicies(completionPolicies);
//                        template.setCompletionPolicy(completionPolicy);

                        /*
                           [simpleLimitExceptionHandler]
                           RepeatTemplate > isComplete() 의 결과값으로 반복 여부 확인
                           예외 제한 횟수만큼 실행 - 아이템 개수만큼 (3개일때 최대에러 3개면 9번)

                           simpleLimitExceptionHandler 는 빈으로 등록해야한다.
                           -> 빈의 라이프 사이클 중에 afterPropertiesSet()를 호출하게 되는데, 여기서 예외가 발생할 경우
                           처리해야하는 초기화 설정을 하게된다. 이 처리가 되지 않으면 limit 값이 제대로 설정되지 않는다.
                           SimpleLimitExceptionHandler 를 빈으로 정의해야만 afterPropertiesSet()이 호출된다.
                           일반 객체로 생성하면 빈이 아니기 때문에 afterPropertiesSet()이 호출되지 않아서 limit 값이 0으로 셋팅되버린다.
                         */
                        // 예외 제한 횟수만큼 반복문 실행
                        template.setExceptionHandler(simpleLimitExceptionHandler());

                        template.iterate(new RepeatCallback() {

                            // 비즈니스 로직
                            public RepeatStatus doInIteration(RepeatContext context) {
                                System.out.println("repeatTest");
                                throw new RuntimeException("Exception is occurred");
//                                return RepeatStatus.CONTINUABLE;
                            }

                        });

                        return item;
                    }
                })
                .writer(new ItemWriter<String>() {
                    @Override
                    public void write(List<? extends String> items) throws Exception {
                        System.out.println(items);
                    }
                })
                .build();
    }

    @Bean
    public SimpleLimitExceptionHandler simpleLimitExceptionHandler(){
        return new SimpleLimitExceptionHandler(2);
    }
}
