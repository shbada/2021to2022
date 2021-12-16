package com.batch.order.batch;

import com.batch.order.entity.User;
import com.batch.order.order.JobParametersDecide;
import com.batch.order.order.OrderStatistics;
import com.batch.order.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class UserConfiguration {
    private final int CHUNK = 100;

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final UserRepository userRepository;
    private final EntityManagerFactory entityManagerFactory;
    private final DataSource dataSource;

    /**
     * --job.name=userJob
     * -date=2020-11 --job.name=userJob
     * @return
     */
    @Bean
    public Job userJob() throws Exception {
        return this.jobBuilderFactory.get("userJob")
                .incrementer(new RunIdIncrementer()) // Batch parameter increment
                /* 실행할 스텝 지정 */
                .start(this.userStep())
                .next(this.userLevelUpStep())
                /* 실행할 리스너 지정 */
                .listener(new LevelUpJobExecutionListener(userRepository))
                //.next(this.orderStatisticsStep(null))
                /** orderStatisticsStep 추가 */
                .next(new JobParametersDecide("date"))
                    .on(JobParametersDecide.CONTINUE.getName()) /* CONTINUE 일때 */
                    .to(this.orderStatisticsStep(null))
                    .build()
                .build();
    }

    /**
     * 주문 집계 Step
     * @param date
     * @return
     */
    @Bean
    @JobScope
    public Step orderStatisticsStep(@Value("#{jobParameters[date]}") String date) throws Exception {
        return this.stepBuilderFactory.get("orderStatisticsStep")
                .<OrderStatistics, OrderStatistics>chunk(CHUNK)
                .reader(this.orderStatisticsItemReader(date))
                .writer(this.orderStatisticsItemWriter(date))
                .build();
    }

    /**
     * itemReader 에서 읽어온 OrderStatistics 기준으로 파일 생성하기
     * @param date
     * @return
     */
    private ItemWriter<? super OrderStatistics> orderStatisticsItemWriter(String date) throws Exception {
        /**
         * step 이 끝나는 시점에 파일을 한번에 생성하기 때문에
         * chunk 실행 개수만큼 파일이 여러개 생성되지 않음
         */
        YearMonth yearMonth = YearMonth.parse(date);

        /* 파일명 지정 */
        String fileName = yearMonth.getYear() + "년_" + yearMonth.getMonthValue() + "월_일별_주문_금액.csv";

        BeanWrapperFieldExtractor<OrderStatistics> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[] {"amount", "date"});

        DelimitedLineAggregator<OrderStatistics> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setDelimiter(","); /* 구분자 */
        lineAggregator.setFieldExtractor(fieldExtractor);

        FlatFileItemWriter<OrderStatistics> itemWriter = new FlatFileItemWriterBuilder<OrderStatistics>()
                .resource(new FileSystemResource("excelfile/" + fileName))
                .lineAggregator(lineAggregator)
                .name("orderStatisticsItemWriter")
                .encoding("UTF-8")
                .headerCallback(writer -> writer.write("total_amount, date"))
                .build();

        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    /**
     * 주문의 일별 합산된 금액을 조회
     * @param date
     * @return
     */
    private ItemReader<? extends OrderStatistics> orderStatisticsItemReader(String date) throws Exception {
        YearMonth yearMonth = YearMonth.parse(date);

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("startDate", yearMonth.atDay(1));
        parameters.put("endDate", yearMonth.atEndOfMonth()); // 해당 월의 마지막 일로 체크됨

        Map<String, Order> sortKey = new HashMap<>();
        sortKey.put("created_date", Order.ASCENDING); // 정렬 오름차순

        JdbcPagingItemReader<OrderStatistics> itemReader = new JdbcPagingItemReaderBuilder<OrderStatistics>()
                .dataSource(this.dataSource)
                .rowMapper((resultSet, i) -> OrderStatistics.builder()
                        .amount(resultSet.getString(1))
                        .date(LocalDate.parse(resultSet.getString(2), DateTimeFormatter.ISO_DATE))
                        .build()) // 조회된 orders 데이터 기준으로 OrderStatistics 로 매핑
                .pageSize(CHUNK)
                .name("orderStatisticsItemReader")
                /* select 쿼리 */
                .selectClause("sum(amount), created_date") /* 일별 합계 */
                .fromClause("orders") /* orders 데이터 조회 */
                .whereClause("created_date >= :startDate and created_date <= :endDate")
                .groupClause("created_date")
                .parameterValues(parameters) // startDate, endDate 파라미터 매핑 위해
                .sortKeys(sortKey)
                .build();

        itemReader.afterPropertiesSet();

        return itemReader;
    }

    /**
     * Tasklet Step
     * @return
     */
    @Bean
    public Step userStep() {
        return this.stepBuilderFactory.get("userStep")
                /* user Data 400 개 생성 */
                /* Step 의 구성방법 1. Tasklet 사용 */
                .tasklet(new SaveUserTasklet(userRepository))
                .build();
    }

    /**
     * Chunk Step
     * @return
     * @throws Exception
     */
    @Bean
    public Step userLevelUpStep() throws Exception {
        /* Step 의 구성 방법 2. Chunk (reader-processor-writer) */
        return this.stepBuilderFactory.get("userLevelUpStep")
                .<User, User>chunk(CHUNK)
                .reader(this.itemReader())
                .processor(this.itemProcessor())
                .writer(this.itemWriter())
                .build();
    }

    /**
     * ItemWriter
     * @return
     */
    private ItemWriter<? super User> itemWriter() {
        return users -> {
            users.forEach(x -> {
                x.levelUp(); // 레벨업 실행
                userRepository.save(x);
                log.info("id: " + x.getId());
            });
        };
    }

    /**
     * ItemProcessor
     * @return
     */
    private ItemProcessor<? super User,? extends User> itemProcessor() {
        /* 등급 상향 유저 대상 추출 */
        return user -> {
            if (user.availableLevelUp()) { /* 등급 상향 유저 여부 체크 */
                log.info("user update target id: " + user.getId());
                return user;
            }

            return null;
        };
    }

    /**
     * ItemReader
     * @return
     * @throws Exception
     */
    private ItemReader<? extends User> itemReader() throws Exception {
        /* JpaPagingItemReaderBuilder */
        JpaPagingItemReader<User> itemReader = new JpaPagingItemReaderBuilder<User>()
                .queryString("select u from User u")
                .entityManagerFactory(entityManagerFactory) // JPA 사용시
                .pageSize(CHUNK) /* 정크 사이즈와 동일하게 보통 설정함. */
                .name("userItemReader")
                .build();

        itemReader.afterPropertiesSet();
        return itemReader;

    }
}
