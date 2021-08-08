package com.example.kafkaProducer.controller;

import com.example.kafkaProducer.common.CommonResponse;
import com.example.kafkaProducer.dto.KafkaTestDto;
import com.example.kafkaProducer.kafka.KafkaProducer;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"KafkaController"})
@RestController
@RequestMapping("/test/")
@RequiredArgsConstructor
public class KafkaController {

    private final CommonResponse commonResponse;
    private final Environment env;
    private final KafkaProducer kafkaProducer;

    /**
     * kafka 테스트 API
     * @return
     */
    @GetMapping("/kafka")
    public ResponseEntity<?> kafkaProducerTest() {
        KafkaTestDto kafkaTestDto = new KafkaTestDto();
        kafkaTestDto.setTopicName("test");
        kafkaTestDto.setProducerName("user");
        kafkaTestDto.setConsumerName("order");

        /* 카프카에 메시지 전달 실행 */
        String resultString = kafkaProducer.send("test", kafkaTestDto);

        return commonResponse.send(resultString);
    }
}
