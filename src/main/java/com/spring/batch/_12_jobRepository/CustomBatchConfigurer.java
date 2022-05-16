package com.spring.batch._12_jobRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Slf4j
@Configuration
public class CustomBatchConfigurer extends BasicBatchConfigurer {
    private final DataSource dataSource;

    protected CustomBatchConfigurer(BatchProperties properties, DataSource dataSource, TransactionManagerCustomizers transactionManagerCustomizers) {
        super(properties, dataSource, transactionManagerCustomizers);
        this.dataSource = dataSource;
    }

    /**
     * JobRepository custom
     * @return
     * @throws Exception
     */
    @Override
    protected JobRepository createJobRepository() throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource); // 기본값 설정되고, 우리가 만든 파일도 따로 지정 가능
        jobRepositoryFactoryBean.setTransactionManager(getTransactionManager()); // 스프링배치가 제공해줌
        jobRepositoryFactoryBean.setIsolationLevelForCreate("ISOLATION_READ_COMMITTED");
        // tablePrefix 를 바꾸면 테이블을 직접 생성해줘야한다.
        // 기존에는 BATCH_ 로 시작하는 테이블이 존재하기 때문이다.
        jobRepositoryFactoryBean.setTablePrefix("SYSTEM_"); // 기본값 BATCH_

        return jobRepositoryFactoryBean.getObject(); // JobRepository Type return
    }
}
