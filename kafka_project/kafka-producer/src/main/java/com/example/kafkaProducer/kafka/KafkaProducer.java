package com.example.kafkaProducer.kafka;

import com.example.kafkaProducer.dto.KafkaTestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * kafka message 전송
     * @param topic
     * @param kafkaTestDto
     * @return
     */
    public String send(String topic, KafkaTestDto kafkaTestDto) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(kafkaTestDto);

        } catch (JsonProcessingException je) {
            je.printStackTrace();
        }

        kafkaTemplate.send(topic, jsonInString);
        log.info("Kafka Producer test: " + kafkaTestDto);

        return jsonInString;
    }
}
