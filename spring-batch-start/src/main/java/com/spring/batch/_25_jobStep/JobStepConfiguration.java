package com.spring.batch._25_jobStep;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.job.DefaultJobParametersExtractor;
import org.springframework.batch.core.step.job.JobParametersExtractor;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
--job.name=parentJob
 */

@Configuration
@RequiredArgsConstructor
public class JobStepConfiguration {

    // job 생성
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job parentJob() {
        return this.jobBuilderFactory.get("parentJob")
                .start(jobStep(null))
                .next(jobStepStep2())
                .build();
    }

    @Bean
    public Step jobStep(JobLauncher jobLauncher) {
        return stepBuilderFactory.get("jobStep")
                .job(childJob()) // 별도의 메타 job 으로 관리됨
                .launcher(jobLauncher)
                .parametersExtractor(jobParametersExtractor())
                .listener(new StepExecutionListener() {
                    @Override
                    public void beforeStep(StepExecution stepExecution) {
                        // jobParametersExtractor 에서 처리될 파라미터 저장
                        stepExecution.getExecutionContext().putString("name", "user1");
                    }

                    @Override
                    public ExitStatus afterStep(StepExecution stepExecution) {
                        return null;
                    }
                })
                .build();
    }

    private DefaultJobParametersExtractor jobParametersExtractor() {
        DefaultJobParametersExtractor extractor = new DefaultJobParametersExtractor();
        extractor.setKeys(new String[]{"name"}); // key
        // name 에 해당하는 key 를 찾는다.
        // 그 key 에 해당하는 값들을 가져온다.
        return extractor; // user1
    }

    @Bean
    public Job childJob() {
        return this.jobBuilderFactory.get("childJob")
                .start(jobStepStep1())
                .build();
    }

    @Bean
    public Step jobStepStep1() {
        return stepBuilderFactory.get("jobStepStep1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("jobStepStep1");
                        // 에러가 발생하면?
                        // childJob 실패 - jobStep 실패 - ParentJob 실패
                        // throw new RuntimeException("step1 was failed");
                       return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }

    @Bean
    public Step jobStepStep2() {
        return stepBuilderFactory.get("jobStepStep2")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("jobStepStep2");

                        // step2만 실패할 경우?
                        // ParentJob - FAIL (실패한 Step2 는 parentJob 이 가지고있음)
                        // ChildJob - COMPLETED
                        // 만약 ChildJbo 이 실패했다면 ParentJob 도 함께 실패였을거고,
                        // ParentJob 만 실패했을땐 ChildJob 은 성공
                        throw new RuntimeException("step1 was failed");
                        // return RepeatStatus.FINISHED;
                    }
                })
                .build();
    }
}
