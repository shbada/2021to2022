package com.project.springbatch._51_reader_xml;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.item.xml.builder.StaxEventItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import java.util.HashMap;
import java.util.Map;

/*
--job.name=xmlBatchJob
 */
@RequiredArgsConstructor
@Configuration
public class XMLConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job xmlBatchJob() {
        return jobBuilderFactory.get("xmlBatchJob")
                .incrementer(new RunIdIncrementer())
                .start(xmlBatchStep1())
                .build();
    }

    @Bean
    public Step xmlBatchStep1() {
        return stepBuilderFactory.get("xmlBatchStep1")
                .<Customer, Customer>chunk(3)
                .reader(customXmlItemReader())
                .writer(customXmlItemWriter())
                .build();
    }

    @Bean
    public StaxEventItemReader<Customer> customXmlItemReader() {
        return new StaxEventItemReaderBuilder<Customer>()
                .name("customXmlItemReader")
                .resource(new ClassPathResource("item51/customer.xml"))
                .addFragmentRootElements("customer")
                .unmarshaller(itemMarshaller())
                .build();
    }

    /*@Bean
    public StaxEventItemReader<Customer> customItemReader() {
        XStreamMarshaller unmarshaller = new XStreamMarshaller();

        Map<String, Class> aliases = new HashMap<>();
        aliases.put("customer", Customer.class);
        aliases.put("id", Long.class);
        aliases.put("firstName", String.class);
        aliases.put("lastName", String.class);
        aliases.put("birthdate", Date.class);

        unmarshaller.setAliases(aliases);

        StaxEventItemReader<Customer> reader = new StaxEventItemReader<>();
        reader.setResource(new ClassPathResource("customers.xml"));
        reader.setFragmentRootElementName("customer");
        reader.setUnmarshaller(unmarshaller);

        return reader;
    }*/

    @Bean
    public ItemWriter<Customer> customXmlItemWriter() {
        return items -> {
            for (Customer item : items) {
                System.out.println(item.toString());
            }
        };
    }

    @Bean
    public XStreamMarshaller itemMarshaller() {
        Map<String, Class<?>> aliases = new HashMap<>();

        aliases.put("customer", Customer.class); // tag 전체 타입
        aliases.put("id", Long.class); // 두번째부터는 각 항목의 타입
        aliases.put("name", String.class);
        aliases.put("age", Integer.class);

        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
        xStreamMarshaller.setAliases(aliases);

        return xStreamMarshaller;
    }
}
