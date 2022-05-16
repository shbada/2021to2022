package com.spring.batch;

/**
 * @SpringBatchTest
 * = 자동으로 applicationContext에 테스트에 필요한 여러 유틸 Bean을 등록해주는 어노테이션
 * 1) JobLauncherTestUtils
 * - launchJob, laucnStep()과 같은 스프링 배치 테스트에 필요한 유틸성 메서드 지원
 *
 * 2) JobrepositoryTestUtils
 * - JobRepository를 사용해서 JobExecution 을 생성 및 삭제 기능 메서드 지원
 *
 * 3) StepScopeTestExecutionListener
 * - @StepScope 컨텍스트를 생성해주며 해당 컨텍스트를 통해 JobParameter 등을 단위 테스트에서 DI 받을 수 있따
 *
 * 4) JobScopeTestExecutionListener
 * - @JobScope 컨텍스트를 생성해주며 해당 컨텍스트를 통해 JobParameter 등을 단위 테스트에서 DI 받을 수 있다.
 */

import com.spring.batch._16_STEP_종료코드.SimpleJobConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.*;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

/**
 * @SpringBatchTest 는 Spring Batch 만을 위한 어노테이션
 * @SpringBootTest 선언을 같이 해줘야한다. -> job 설정 클래스 지정, 통합 테스트를 위한 여러 의존성 빈들을 주입 받기 위한 어노테이션
 * @EnableBatchProcessing
 * - 테스트시 배치 환경 및 설정 초기화를 자동 구동하기 위한 어노테이션
 * - 테스트 클래스마다 선언하지 않고 공통으로 사용하기 위함
 */
@RunWith(SpringRunner.class)
@SpringBatchTest
@SpringBootTest(classes = {SimpleJobConfiguration.class, TestBatchConfig.class}) // 1개만 지정
public class SimpleJobTest {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils; // launchJob, laucnStep()과 같은 스프링 배치 테스트에 필요한 유틸성 메서드 지원

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void simpleJob_test() throws Exception {
        // given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("name", "user1")
                .addLong("date", new Date().getTime())
                .toJobParameters();

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        // then
        Assert.assertEquals(jobExecution.getStatus(), BatchStatus.COMPLETED);
        Assert.assertEquals(jobExecution.getExitStatus(), ExitStatus.COMPLETED);
    }

    @Test
    public void simpleStep_test() throws Exception {
        // given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("name", "user1")
                .addLong("date", new Date().getTime())
                .toJobParameters();

        /** step 1개만 테스트 */
        JobExecution jobExecution2 = jobLauncherTestUtils.launchStep("step1");

        // stepExecution 꺼내기
        StepExecution stepExecution = (StepExecution) ((List) jobExecution2.getStepExecutions()).get(0);

        // 1000 개의 데이터를 10번(chunkSize)씩 하고 + 11번째에 reader 가 돌고 데이터가 없는지 체크 후 종료 -> 11
        // 11번째에도 데이터가 없더라도 commit 은 발생한다.
        Assert.assertEquals(stepExecution.getCommitCount(), 11);
        Assert.assertEquals(stepExecution.getReadCount(), 1000);
        Assert.assertEquals(stepExecution.getWriteCount(), 1000);
    }
}
