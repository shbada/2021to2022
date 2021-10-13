package com.example.kafka.producer;

import com.example.kafka.kafka.CustomJacksonConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {
    /* 토픽명 */
    public static final String TOPIC_NAME = "thecodinglive";

    /* Json 문자열로 보내기 때문 */
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 메시지 전송
     * @param topic
     * @param data
     */
    public void send(String topic, Object data) {
        try {
            kafkaTemplate.send(topic, CustomJacksonConverter.toJson(data));
        } catch (Exception e) {
            System.err.println("error" +  e.getMessage());
        }
    }
}
