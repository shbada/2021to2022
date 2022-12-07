package com.spring.batch._77_operation;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.SimpleJob;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Set;

@RestController
@RequiredArgsConstructor
public class JobController {

    /*
     * 생성된 Job을 자동으로 등록, 추적 및 관리하며 여러 곳에서 job을 생성한 경우 ApplicationContext 에서 job을 수집해서 사용할 수 있다.
     * jobName 을 key로 하고 job 을 값으로 하여 매핑한다.
     *
     * JobRegistryBeanPostProcessor - BeanPostProcessor 단계에서 bean 초기화시 자동으로 JobRegistry 에 Job을 등록시켜준다.
     */
    private final JobRegistry jobRegistry;

    /*
    JobExplorer, JobRepository, JobRegistry, JobLauncher 를 포함하고있다.
    배치의 중단, 재시작, job 요약 등의 모니터링이 가능하다.
    기본 구현체로 SimpleJobOperator 클래스를 제공한다.
     */
    private final JobOperator jobOperator;

    /*
    JobRepository 의 readonly 버전
    실행중인 Job의 실행정보인 JobExecution 또는 Step의 실행 정보인 StepExecution을 조회할 수 있다.
     */
    private final JobExplorer jobExplorer;

    @PostMapping(value = "/batch/start")
    public String start(@RequestBody JobInfo jobInfo) throws Exception {

        for(Iterator<String> iterator = jobRegistry.getJobNames().iterator(); iterator.hasNext();){

            SimpleJob job = (SimpleJob)jobRegistry.getJob(iterator.next()); // jobName 으로 job 조회
            System.out.println("job name: " + job.getName());

            jobOperator.start(job.getName(), "id=" + jobInfo.getId());
        }

        return "batch is started";
    }

    /**
     * Job 이 성공으로 끝나면 재시작이 불가능
     * Job 이 실패되었어야 restart 가 가능하다.
     * 그러면 실패한 Job의 정보를 가져오자.
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/batch/restart")
    public String restart() throws Exception {

        /*
         기존의 값이 있어야만 실행된 job 에 대한 정보를 가져올 수 있다.
         한번 실행시키자.
         BATCH_JOB_INSTANCE
         BATCH_J0B_EXECUTION
         BATCH_STEP_EXECUTION
         */
        for(Iterator<String> iterator = jobRegistry.getJobNames().iterator(); iterator.hasNext();){

            SimpleJob job = (SimpleJob)jobRegistry.getJob(iterator.next());
            System.out.println("job name: " + job.getName());

            // /batch/stop 수행 후 batch/restart 수행하기
            // 마지막 최종적으로 실행한 Job 의 정보를 가져와서 restart 한다.
            JobInstance lastJobInstance = jobExplorer.getLastJobInstance(job.getName());
            JobExecution lastJobExecution = jobExplorer.getLastJobExecution(lastJobInstance);
            jobOperator.restart(lastJobExecution.getId());

        }

        return "batch is restarted";
    }

    @PostMapping(value = "/batch/stop")
    public String stop() throws Exception {

        for(Iterator<String> iterator = jobRegistry.getJobNames().iterator(); iterator.hasNext();){

            SimpleJob job = (SimpleJob)jobRegistry.getJob(iterator.next());
            System.out.println("job name: " + job.getName());

            // 현재 실행중인 Job 의 JobExecution 을 모두 가져온다. (Job 의 개수만큼 존재하겠다)
            Set<JobExecution> runningJobExecutions = jobExplorer.findRunningJobExecutions(job.getName());
            JobExecution jobExecution = runningJobExecutions.iterator().next();

            // 바로 중단되지는 않고, 현재 실행중인 Step까지는 실행하고 중단한다.
            jobOperator.stop(jobExecution.getId());
        }

        return "batch is stopped";
    }
}
