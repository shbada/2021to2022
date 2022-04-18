package com.app.springbatch.scheduler;

import lombok.RequiredArgsConstructor;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.HashMap;

import static org.quartz.JobBuilder.newJob;

@Component
@RequiredArgsConstructor
public class FileJobRunner extends JobRunner {

    private final Scheduler scheduler;

    @Override
    protected void doRun(ApplicationArguments args) {

        String[] sourceArgs = args.getSourceArgs();
        JobDetail jobDetail = buildJobDetail(FileSchJob.class, "fileJob", "batch", new HashMap());

        // 매 50초마다
        Trigger trigger = buildJobTrigger("0/50 * * * * ?");

        // param set
        // argument 에 20220418 만 쓰면됨 (sourceArgs 에 들어감) (원래는 부모 JobRunner 의 run() 매개변수)
        jobDetail.getJobDataMap().put("requestDate", sourceArgs[0]);

        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
