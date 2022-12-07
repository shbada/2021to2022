package com.app.springbatch.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class FileSchJob extends QuartzJobBean{

    /*
     * fileJob 보다 apiJob 이 먼저 실행되면 DB가 비어져있는 순간이 있다.
     * -> 최초에만 그럴 수 있고, 그 후로는 정상적으로 동작됨
     */

    private final Job fileJob;
    private final JobLauncher jobLauncher;
    private final JobExplorer jobExplorer; // 배치 모니터링 기능 제공

    @SneakyThrows
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        String requestDate = (String) context.getJobDetail().getJobDataMap().get("requestDate");

        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("id", new Date().getTime())
                .addString("requestDate", requestDate)
                .toJobParameters();

        /*
          BATCH_JOB_EXECUTION_PARAMS 에 key - val 이 저장된다.
          (JOB_EXECUTION_ID : PK)
          이 데이터를 참조해서 지금 실행하는 배치에서의 날짜 데이터의 값이 이미 테이블에 저장되어있는 이 날짜와 비교하여,
          이미 저장되어있다면 배치를 실행하지 않겠다.
         */

        // BATCH_JOB_INSTANCE 테이블의 동일한 Job 에 해당하는 여러개의 job instance 를 가져온다.
        int jobInstanceCount = jobExplorer.getJobInstanceCount(fileJob.getName());

        List<JobInstance> jobInstances = jobExplorer.getJobInstances(fileJob.getName(), 0, jobInstanceCount);

        if (jobInstances.size() > 0) {
            for (JobInstance jobInstance : jobInstances) {
                // jobExecution List 얻기
                List<JobExecution> jobExecutions = jobExplorer.getJobExecutions(jobInstance);

                // 중복 데이터 가져오기
                List<JobExecution> jobExecutionList = jobExecutions.stream()
                        .filter(jobExecution ->
                                jobExecution.getJobParameters().getString("requestDate").equals(requestDate))
                        .collect(Collectors.toList());

                if (jobExecutionList.size() > 0) {
                    throw new JobExecutionException(requestDate + " already exists");
                }
            }
        }

        jobLauncher.run(fileJob, jobParameters);
    }

}
