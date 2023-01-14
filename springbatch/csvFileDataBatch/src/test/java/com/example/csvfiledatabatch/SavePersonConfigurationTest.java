package com.example.csvfiledatabatch;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBatchTest // @JobScope 를 동작하게 하기 위해
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SavePersonConfiguration.class, TestConfiguration.class})
public class SavePersonConfigurationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private PersonRepository personRepository;

    @After
    public void tearDown() throws Exception {
        personRepository.deleteAll();
    }

    /**
     * Step 단위테스트
     */
    @Test
    public void test_step() {
        // launchStep 도 잡 파라미터를 받을 수 있다.
        JobExecution jobExecution = jobLauncherTestUtils.launchStep("savePersonStep");

        // then
        /* Job 은 N개의 Step 을 가질 수 있는데, 해당 스텝 별 write 된 count 를 sum 한다 */
        /* savePersonStep 1개 이므로, savePersonStep 이 스텝의 개수만 가져오게될 것 */
        Assertions.assertThat(jobExecution.getStepExecutions().stream()
                .mapToInt(StepExecution::getWriteCount)
                .sum()
        ).isEqualTo(4);
    }

    @Test
    public void test_allow_duplicate() throws Exception {
        // given
        /* Job parameter 설정 */
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("allow_duplicate", "false")
                .toJobParameters();

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        // then
        /* Job 은 N개의 Step 을 가질 수 있는데, 해당 스텝 별 write 된 count 를 sum 한다 */
        /* savePersonStep 1개 이므로, savePersonStep 이 스텝의 개수만 가져오게될 것 */
        Assertions.assertThat(jobExecution.getStepExecutions().stream()
                .mapToInt(StepExecution::getWriteCount)
                .sum()
        ).isEqualTo(4);
    }

    @Test
    public void test_not_allow_duplicate() throws Exception {
        // given
        /* Job parameter 설정 */
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("allow_duplicate", "true")
                .toJobParameters();

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        // then
        /* Job 은 N개의 Step 을 가질 수 있는데, 해당 스텝 별 write 된 count 를 sum 한다 */
        /* savePersonStep 1개 이므로, savePersonStep 이 스텝의 개수만 가져오게될 것 */
        Assertions.assertThat(jobExecution.getStepExecutions().stream()
                .mapToInt(StepExecution::getWriteCount)
                .sum()
        ).isEqualTo(100);
    }

    @Test
    public void test_jpa_allow_duplicate() throws Exception {
        // given
        /* Job parameter 설정 */
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("allow_duplicate", "false")
                .toJobParameters();

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        // then
        /* Job 은 N개의 Step 을 가질 수 있는데, 해당 스텝 별 write 된 count 를 sum 한다 */
        /* savePersonStep 1개 이므로, savePersonStep 이 스텝의 개수만 가져오게될 것 */
        Assertions.assertThat(jobExecution.getStepExecutions().stream()
                .mapToInt(StepExecution::getWriteCount)
                .sum()
        ).isEqualTo(personRepository.count())
         .isEqualTo(4);
    }

    @Test
    public void test_jpa_not_allow_duplicate() throws Exception {
        // given
        /* Job parameter 설정 */
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("allow_duplicate", "true")
                .toJobParameters();

        // when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        // then
        /* Job 은 N개의 Step 을 가질 수 있는데, 해당 스텝 별 write 된 count 를 sum 한다 */
        /* savePersonStep 1개 이므로, savePersonStep 이 스텝의 개수만 가져오게될 것 */
        Assertions.assertThat(jobExecution.getStepExecutions().stream()
                .mapToInt(StepExecution::getWriteCount)
                .sum()
        ).isEqualTo(personRepository.count())
         .isEqualTo(100);
    }
}