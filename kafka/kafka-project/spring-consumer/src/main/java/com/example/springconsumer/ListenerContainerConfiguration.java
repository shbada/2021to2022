package com.example.springconsumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.*;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 서로 다른 설정을 가진 2개 이상의 리스너를 구현하거나 리밸런스 리스너를 구현하기 위해서는
 * 커스텀 리스너 컨테이너를 사용해야한다.
 * 커스텀 리스너 컨테이너를 만들기 위해서 스프링 카프카에서 카프카 리스너 컨테이너 팩토리 인스턴스를 생성해야한다. (KafkaListenerContainerFactory)
 * 카프카 리스너 컨테이너 팩토리를 빈으로 등록하고 KafkaListener 어노테이션에서 커스텀 리스너 컨테이너 팩토리를 등록하면 커스텀 리스너 컨테이너를 사용할 수 있다.
 */
@Configuration
public class ListenerContainerConfiguration {

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> customContainerFactory() {

        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        /* DefaultKafkaConsumerFactory 는 리스너 컨테이너 팩토리를 생성할때 컨슈머 기본 옵션을 설정하는 용도로 사용된다. */
        DefaultKafkaConsumerFactory cf = new DefaultKafkaConsumerFactory<>(props);

        /* ConcurrentKafkaListenerContainerFactory 는 리스너 컨테이너를 만들기위해 사용한다. */
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

        /* 리밸런스 리스너를 선언한다. */
        factory.getContainerProperties().setConsumerRebalanceListener(new ConsumerAwareRebalanceListener() {
            /**
             * 커밋이 되기 전에 리밸런스가 발생했을때
             * @param consumer the consumer.
             * @param partitions the partitions.
             */
            @Override
            public void onPartitionsRevokedBeforeCommit(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {

            }

            /**
             * 커밋이 된 후에 리밸런스가 발생했을때
             * @param consumer the consumer.
             * @param partitions the partitions.
             */
            @Override
            public void onPartitionsRevokedAfterCommit(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {

            }

            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

            }

            @Override
            public void onPartitionsLost(Collection<TopicPartition> partitions) {

            }
        });
        /* 레코드 리스너를 사용함을 명시 */
        factory.setBatchListener(false);
        /* AckMode 설정 */
        factory.getContainerProperties().setAckMode(ContainerProperties.AckMode.RECORD);
        /* 컨슈머 팩토리 설정 */
        factory.setConsumerFactory(cf);

        return factory;
    }
}
