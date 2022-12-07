package com.example.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.kafka.producer.KafkaProducer.TOPIC_NAME;

@Component
public class KafkaReceiver {
    // @RabbitListener (RabbitMQ) 비슷하다

    /**
     * 메시지 받을 경우 실행
     * @param event
     */
    @KafkaListener(topics = TOPIC_NAME, autoStartup = "true")
    public void eventHandler(Object event) {
        System.out.println("get data: "+ event);
    }

}
