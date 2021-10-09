package com.spring.batch.part3;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class ChunkProcessingConfiguration {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job chunkProcessingJob() { /* Job : 배치 실행 단위 */
        return jobBuilderFactory.get("chunkProcessingJob") /* 잡 이름 : helloWorld, 배치를 실행시키는 key */
                .incrementer(new RunIdIncrementer()) /* 항상 잡이 실행될때마다 파라미터ID를 자동으로 생성해주는 클래스 */
                .start(this.taskBaseStep())
                .next(this.chunkBaseStep())
                .build();
    }

    /**
     * taskBaseStep
     * @return
     */
    @Bean
    public Step taskBaseStep() { /* tasklet 기반 */
        return stepBuilderFactory.get("taskBaseStep") /* 스텝 이름 */
                .tasklet(this.tasklet())
                .build();
    }

    /**
     * chunkBaseStep
     * @return
     */
    @Bean
    public Step chunkBaseStep() {
        return stepBuilderFactory.get("chunkBaseStep")
                /* <INPUT, OUTPUT */
                .<String, String>chunk(10) /* 100 개의 데이터를 10개씩 나눠서 실행한다는 의미이다. (페이징 처리처럼) */
                /**
                 * chunkSize 만큼 reader, processor 이 실행되고 마지막으로 writer 이 실행되는 것이다.
                 * 이는 reader 10번, processor 10번 -> writer 에 리스트 size 10개가 전달된다는 의미이다.
                 */
                .reader(this.itemReader()) /* Reader : INPUT 1개씩 처리하고 INPUT TYPE 으로 반환 (String, String) */
                .processor(this.itemProcessor()) /* Processor : INPUT 1개씩 처리하고 OUTPUT TYPE 으로 반환 (String, String) */
                .writer(this.itemWriter()) /* Writer : LIST<OUTPUT> 타입으로 변환되어 전달 */
                .build();
    }


    /**
     * ItemReader
     * @return
     */
    private ItemReader<String> itemReader() {
        /* 스프링 배치에서 제공해주는 Reader */
        return new ListItemReader<>(getItems()); /* INPUT 타입을 리턴 : String */
    }

    /**
     * ItemProcessor
     * @return
     */
    private ItemProcessor<String, String> itemProcessor() {
        /* 결국, itemReader 에서 생성한 getItems() 호출 결과의 리스트에 해당 문자열을 더한다. */
        return item -> item + ", Spring Batch"; /* INPUT 을 받아, processing 후 OUTPUT 을 리턴 */
    }

    /**
     * ItemWriter
     * @return
     */
    private ItemWriter<? super String> itemWriter() {
        /* 일괄 처리 */
        // return items -> log.info("chunk item size : {}", items.size()); /* List<OUTPUT> 을 받음 */
        /* 100 개가 찍히는걸 확인할 수 있다. */
        return items -> items.forEach(log::info);
    }

    /**
     * Tasklet
     * @return
     */
    private Tasklet tasklet() {
        return (stepContribution, chunkContext) -> {
            List<String> items = getItems();

//            /* tasklet 에서 chunk 처럼 코딩하기 */
//            StepExecution stepExecution = stepContribution.getStepExecution();
//
//            int chunkSize = 10;
//            int fromIndex = stepExecution.getReadCount(); /* chunk 에서 읽은 아이템 count */
//            int toIndex = fromIndex + chunkSize; // fromIndex 부터 10개의 아이템을 읽을 Index
//
//            if (fromIndex >= items.size()) {
//                return RepeatStatus.FINISHED;
//            }
//
//            /* 인덱스를 기준으로 중간 리스트를 읽어준다 */
//            List<String> getSubList = items.subList(fromIndex, toIndex);
//            log.info("task item size : {}", getSubList.size());
//
//            stepExecution.setReadCount(toIndex); /* 어디까지 처리했는지 저장 */
//            return RepeatStatus.CONTINUABLE; /* 해당 Tasklet 을 계속해서 실행하라는 의미 (재실행) */

            log.info("task item size : {}", items.size());

            return RepeatStatus.FINISHED;
        };
    }

    /**
     * Item 데이터 생성하기
     * @return
     */
    private List<String> getItems() {
        List<String> items = IntStream.range(0, 100)
                .mapToObj(i -> i + " Hello")
                .collect(Collectors.toList());

        return items;
    }
}
