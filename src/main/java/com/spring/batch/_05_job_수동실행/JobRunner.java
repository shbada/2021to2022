//package com.spring.batch.applicationrunner;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.JobParameter;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.JobParametersBuilder;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
///**
// * 스프링부트가 제공하는 ApplicationRunner
// */
//@Component // 빈으로 만든다.
//public class JobRunner implements ApplicationRunner {
//
//    /* 잡을 실행시키는, 스프링부트가 초기화될때 이미 빈으로 생성되어있어서 주입이 가능하다. */
//    @Autowired
//    private JobLauncher jobLauncher;
//
//    /* xxxConfiguration 안의 Job 이 빈이므로 주입이 가능하다. */
//    @Autowired
//    private Job job;
//
//
//    /**
//     * job Parameter add
//     * job 실행을 수동으로 수행
//     * @param args
//     * @throws Exception
//     */
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        // 동일한 job 이 실행되면 에러 발생 :  A job instance already exists and complete for parameters ...
////        JobParameters jobParameters = new JobParametersBuilder()
////                .addString("name", "user1")
////                .toJobParameters();
//
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("name", "user2")
//                .toJobParameters();
//
//        jobLauncher.run(job, jobParameters);
//    }
//}
