package com.example.kafkaConsumer.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class KafkaTestDto implements Serializable {
    private String topicName;
    private String producerName;
    private String consumerName;
}
