package com.example.kafkaConsumer.kafka;

import com.example.kafkaConsumer.dto.KafkaTestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    /**
     * 토픽명 test
     * @param kafkaMessage
     */
    @KafkaListener(topics = "test") // 토픽명
    public void updateQty(String kafkaMessage) { // message 를 토픽에서 가져온다.
        log.info("Kafka Method : " + kafkaMessage);

        KafkaTestDto kafkaTestDto = new KafkaTestDto();
        ObjectMapper mapper = new ObjectMapper();

        try {
            kafkaTestDto = mapper.readValue(kafkaMessage, new TypeReference<KafkaTestDto>() {});
        } catch (JsonProcessingException je) {
            je.printStackTrace();
        }

        log.info("topic: " + kafkaTestDto.getTopicName());
        log.info("producer: " + kafkaTestDto.getProducerName());
        log.info("consumer: " + kafkaTestDto.getConsumerName());
    }
}
