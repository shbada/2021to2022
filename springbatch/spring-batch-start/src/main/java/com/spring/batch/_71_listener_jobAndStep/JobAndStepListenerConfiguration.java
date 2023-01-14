package com.spring.batch._71_listener_jobAndStep;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
@Slf4j
public class JobAndStepListenerConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    /* 빈으로 설정하여 설정 */
    private final CustomStepListener customStepListener;

    /**
     * 1) customJobListener 의 beforeJob
     * 2) step1 - customStepListener 의 beforeStep
     * 3) step1 - customStepListener 의 afterStep
     * 4) step2 - customStepListener 의 beforeStep
     * 5) step2 - customStepListener 의 afterStep
     * 6) CustomJobListener 의 afterStep
     */

    /**
     * 2)~3), 4)~5) 는 다른 스텝이 같은 Listener 을 사용중이다.
     본래의 스프링 배치에서는 동일한 스텝의 getExecutionContext 에서 꺼내오는것.
     (각 스텝별로 독립적인 getExecutionContext 를 가지고있다.)

     step1, step2
     step 은 다르지만 CustomStepListener 가 빈으로 되어있는데, 이게 step1, step2 모두에 등록되어있다.
     만약에 빈이 아닌 new 클래스() 로 등록했다면 step 별로 각각 서로 다른 listener 이 됬겠지만,
     빈으로 설정했기 때문에 step 끼리 데이터를 공유할 수 있다.
     */
    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("batchJob")
                .incrementer(new RunIdIncrementer())
                .start(step1())
                .next(step2())
                .listener(new CustomJobListener()) // JobExecutionListener Type
//                .listener(new CustomAnnotationJobListener()) // Object Type
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
//                        throw new RuntimeException("failed");
                        return RepeatStatus.FINISHED;
                    }
                })
                .listener(customStepListener)
                .build();
    }

    @Bean
    public Step step2() {
        return stepBuilderFactory.get("step2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        return RepeatStatus.FINISHED;
                    }
                })
                .listener(customStepListener)
                .build();
    }
}
