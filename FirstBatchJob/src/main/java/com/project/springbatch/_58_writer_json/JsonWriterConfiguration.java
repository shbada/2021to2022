package com.project.springbatch._58_writer_json;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.batch.item.database.support.PostgresPagingQueryProvider;
import org.springframework.batch.item.json.JacksonJsonObjectMarshaller;
import org.springframework.batch.item.json.JsonFileItemWriter;
import org.springframework.batch.item.json.builder.JsonFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/*
--job.name=jsonWriterJob
*/
@RequiredArgsConstructor
@Configuration
public class JsonWriterConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    @Bean
    public Job jsonWriterJob() throws Exception {
        return jobBuilderFactory.get("jsonWriterJob")
                .incrementer(new RunIdIncrementer())
                .start(jsonWriterStep1())
                .build();
    }

    @Bean
    public Step jsonWriterStep1() throws Exception {
        return stepBuilderFactory.get("jsonWriterStep1")
                .<Customer, Customer>chunk(10)
                .reader(customJsonWriterItemReader())
                .writer(customJsonWriterItemWriter())
                .build();
    }

    @Bean
    public JdbcPagingItemReader<Customer> customJsonWriterItemReader() {

        JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<>();

        reader.setDataSource(this.dataSource);
        reader.setFetchSize(10);
        reader.setRowMapper(new CustomerRowMapper());

        PostgresPagingQueryProvider queryProvider = new PostgresPagingQueryProvider();
        queryProvider.setSelectClause("id, firstName, lastName, birthdate");
        queryProvider.setFromClause("from customer");
        queryProvider.setWhereClause("where firstname like :firstname");

        Map<String, Order> sortKeys = new HashMap<>(1);

        sortKeys.put("id", Order.ASCENDING);
        queryProvider.setSortKeys(sortKeys);
        reader.setQueryProvider(queryProvider);

        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("firstname", "A%");

        reader.setParameterValues(parameters);

        return reader;
    }

    @Bean
    public JsonFileItemWriter<Customer> customJsonWriterItemWriter() {
        return new JsonFileItemWriterBuilder<Customer>()
                .jsonObjectMarshaller(new JacksonJsonObjectMarshaller<>())
//                .resource(new FileSystemResource("/Users/kimseohae/Documents/private-seohae/workspace/springbatch_testapp/FirstBatchJob/src/main/resources/customer.json"))
                .resource(new ClassPathResource("customer.json"))
                .name("customerJsonFileItemWriter")
                .build();
    }
}
