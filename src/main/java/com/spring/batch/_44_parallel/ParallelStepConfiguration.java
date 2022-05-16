package com.spring.batch._44_parallel;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@RequiredArgsConstructor
@Configuration
public class ParallelStepConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job job() {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(flow1())
                // 아래 split() 주석처리 후, 이렇게 되면 메인 스레드가 실행한다. (동기적)
                // .next(flow2())

                // flow1, flow2 병렬 실행
                // flow1(step1), flow2(step2, step3)
                .split(taskExecutor()).add(flow2())
                /*
                  FlowBuilder.java - createState() : SplitState 를 만든다.
                  SplitState.java - handle() : 이걸 실행시킨 주체는 SimpleFlow 다.
                  -> 내부적으로 flows(flow를 담은) 를 가지고있다.
                  -> Callable 안에서 call()가 수행됨
                  -> task 를 수행시키는게 스레드인데, 스레드는 taskExecutor가 생성해주고, .execute(task) 로 수행된다.
                  -> (execute(task); // taskExecutor: 스레드를 생성해주고, execute(task)로 task가 실행되고, task 안에서 simpleFlow가 실행된다.)
                  -> 모든 과정이 끝나게되면 최종 결과를 취합하고 다음 단계로 넘어간다.
                  -> doAggregation(results, executor) -> flow 둘다 완료시, 완료
                  -> doAggregation -> aggregate() 메서드가 값의 최종적인 상태 체크
                 */
                .end()
                .listener(new StopWatchJobListener())
                .build();
    }

    @Bean
    public Flow flow1() {

        TaskletStep step = stepBuilderFactory.get("step1")
                .tasklet(tasklet()).build();

        return new FlowBuilder<Flow>("flow1")
                .start(step)
                .build();
    }

    @Bean
    public Flow flow2() {

        TaskletStep step1 = stepBuilderFactory.get("step2")
                .tasklet(tasklet()).build();

        TaskletStep step2 = stepBuilderFactory.get("step3")
                .tasklet(tasklet()).build();

        return new FlowBuilder<Flow>("flow2")
                .start(step1)
                .next(step2)
                .build();
    }

    @Bean
    public Tasklet tasklet() {
        return new CustomTasklet();
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setThreadNamePrefix("async-thread-");
        return executor;
    }
}
