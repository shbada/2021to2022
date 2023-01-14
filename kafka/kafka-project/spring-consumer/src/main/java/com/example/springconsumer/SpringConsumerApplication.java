package com.example.springconsumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
        
@SpringBootApplication
@Slf4j
public class SpringConsumerApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringConsumerApplication_Batch.class);
        application.run(args);
    }

    /**
     * 가장 기본적인 리스너
     * poll()이 호출되어 가져온 레코드들은 차례대로 개별 레코드의 메시지 값을 파라미터로 받게된다.
     * 파라미터로 컨슈머 레코드를 받기 때문에 메시지 키, 메시지 값에 대한 처리를 이 메서드 안에서 수행하면 된다.
     * @param record
     */
    @KafkaListener(topics = "test",
            groupId = "test-group-00")
    public void recordListener(ConsumerRecord<String,String> record) {
        log.info(record.toString());
    }

    /**
     * 파라미터로 메시지 값을 받는다.
     * @param messageValue
     */
    @KafkaListener(topics = "test",
            groupId = "test-group-01")
    public void singleTopicListener(String messageValue) {
        log.info(messageValue);
    }

    /**
     * properties 옵션을 사용해서 개별 리스너에 카프카 컨슈머 옵션값을 부여할 수 있다.
     * @param messageValue
     */
    @KafkaListener(topics = "test",
            groupId = "test-group-02", properties = {
            "max.poll.interval.ms:60000",
            "auto.offset.reset:earliest"
    })
    public void singleTopicWithPropertiesListener(String messageValue) {
        log.info(messageValue);
    }

    /**
     * 2개 이상의 카프카 컨슈머 스레드를 실행하고 싶다면 concurrency 옵션을 사용하면 된다.
     * concurrency 옵션값에 해당하는만큼 컨슈머 스레드를 만들어서 병렬 처리한다.
     * 예시로, 파티션이 10개인 토픽을 구독할때 가장 좋은 효율은 concurrency 를 10으로 설정하는 것이다.
     * 이렇게 설정하면 10개 파티션에 10개의 컨슈머 스레드가 각각 할당되어 병렬 처리량이 늘어난다.
     * @param messageValue
     */
    @KafkaListener(topics = "test",
            groupId = "test-group-03",
            concurrency = "3")
    public void concurrentTopicListener(String messageValue) {
        log.info(messageValue);
    }

    /**
     * 특정 파티션만 구독하고 싶다면 topicPartitions 파라미터를 사용한다.
     * 여기에 추가로 PartitionOffset 어노테이션을 활용하면 특정 파티션의 특정 오프셋까지 지정할 수 있다.
     * 이 경우에는 그룹 아이디에 관계없이 항상 설정한 오프셋의 데이터부터 가져온다.
     * @param record
     */
    @KafkaListener(topicPartitions =
            {
                    @TopicPartition(topic = "test01", partitions = {"0", "1"}),
                    @TopicPartition(topic = "test02", partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "3"))
            },
            groupId = "test-group-04")
    public void listenSpecificPartition(ConsumerRecord<String, String> record) {
        log.info(record.toString());
    }
}
