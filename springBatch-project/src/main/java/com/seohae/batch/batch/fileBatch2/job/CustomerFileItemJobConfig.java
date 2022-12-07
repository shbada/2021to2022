//package com.seohae.batch.batch.fileBatch2.job;
//
//import com.seohae.batch.batch.fileBatch1.entity.Customer;
//import com.seohae.batch.batch.fileBatch1.mapper.CustomerFieldSetMapper;
//import com.seohae.batch.batch.fileBatch1.mapper.CustomerFileLineTokenizer;
//import lombok.RequiredArgsConstructor;
//import lombok.Value;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.file.FlatFileItemReader;
//import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
//import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
//import org.springframework.batch.item.file.mapping.FieldSetMapper;
//import org.springframework.batch.item.file.mapping.PatternMatchingCompositeLineMapper;
//import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
//import org.springframework.batch.item.file.transform.LineTokenizer;
//import org.springframework.batch.item.file.transform.PatternMatchingCompositeLineTokenizer;
//import org.springframework.batch.item.file.transform.Range;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * --job.name=customerFileItemJobConfig
// */
//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//public class CustomerFileItemJobConfig {
//    private final JobBuilderFactory jobBuilderFactory;
//    private final StepBuilderFactory stepBuilderFactory;
//
//    /**
//     * Job
//     * @return
//     */
//    @Bean
//    public Job flatFileItemJob() {
//        return jobBuilderFactory.get("customerFileItemJobConfig")
//                .incrementer(new RunIdIncrementer())
//                .start(customerFileItemStep())
//                .build();
//    }
//
//    /**
//     * Step
//     * @return
//     */
//    @Bean
//    public Step customerFileItemStep() {
//        return stepBuilderFactory.get("customerFileItemStep")
//                .<Customer, Customer>chunk(10)
//                .reader(customerFileReader())
//                .writer(itemWriter())
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public FlatFileItemReader<Customer> customerItemReader(
//            @Value("#{jobParameters['customerFile']}")Resource inputFile) {
//        return new FlatFileItemReaderBuilder<Customer>()
//                .name("customerItemReader")
//                .lineMapper(lineTokenizer())
//                .resource(inputFile)
//                .build();
//    }
//
//    @Bean
//    public CustomerFileReader customerFileReader() {
//        return new CustomerFileReader(customerItemReader(null));
//    }
//
//    /**
//     * lineMapper 설정
//     * @return
//     */
//    @Bean
//    public PatternMatchingCompositeLineMapper lineTokenizer() {
//        /* LineTokenizer */
//        Map<String, LineTokenizer> lineTokenizers = new HashMap<>(2);
//
//        lineTokenizers.put("CUST*", customerLineTokenizer()); /* 고객 패턴일 경우 */
//        lineTokenizers.put("TRANS*", transactionLineTokenizer()); /* 거래내역 패턴일 경우 */
//
//        /* fieldSetMapper */
//        Map<String, FieldSetMapper> fieldSetMappers = new HashMap<>(2);
//
//        BeanWrapperFieldSetMapper<Customer> customerFieldSetMapper = new BeanWrapperFieldSetMapper<>();
//        customerFieldSetMapper.setTargetType(Customer.class);
//
//        fieldSetMappers.put("CUST*", customerFieldSetMapper); /* 고객 패턴일 경우 */
//        fieldSetMappers.put("TRANS*", new TransactionFieldSetMapper()); /* 거래내역 패턴일 경우 (타입 변환) */
//
//        /* lineMappers */
//        PatternMatchingCompositeLineMapper lineMappers = new PatternMatchingCompositeLineMapper();
//        lineMappers.setTokenizers(lineTokenizers);
//        lineMappers.setFieldSetMappers(fieldSetMappers);
//
//        return lineMappers;
//    }
//
//    @Bean
//    public DelimitedLineTokenizer transactionLineTokenizer() {
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//        lineTokenizer.setNames("prefix", "accountNumber", "transactionDate", "amount");
//
//        return lineTokenizer;
//    }
//
//    @Bean
//    public DelimitedLineTokenizer customerLineTokenizer() {
//        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
//
//        lineTokenizer.setNames("firstName", "middleInitial", "lastName", "address", "city", "state", "zipCode");
//        lineTokenizer.setIncludedFields(1, 2, 3, 4, 5, 6, 7);
//
//        return lineTokenizer;
//    }
//
//    @Bean
//    public ItemWriter<Customer> itemWriter() {
//        return (items) -> items.forEach(System.out::println);
//    }
//}
