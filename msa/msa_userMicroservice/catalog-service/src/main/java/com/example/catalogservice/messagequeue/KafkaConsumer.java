package com.example.catalogservice.messagequeue;

import com.example.catalogservice.jpa.CatalogEntity;
import com.example.catalogservice.jpa.CatalogRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumer {
    private final CatalogRepository repository;

    @KafkaListener(topics = "example-catalog-topic") // 토픽명
    public void updateQty(String kafkaMessage) { // message 를 토픽에서 가져온다.
        log.info("Kafka Method : " + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();

        try {
          map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {});
        } catch (JsonProcessingException je) {
            je.printStackTrace();
        }

        /* 카프카에서 가져온 상품 ID로 정보 조회 */
        CatalogEntity catalogEntity = repository.findByProductId((String) map.get("productId"));

        if (catalogEntity != null) {
            /* 기존 수량에서 카프카에서 얻어온 수량 빼기 */
            catalogEntity.setStock(catalogEntity.getStock() - (Integer) map.get("qty"));
            repository.save(catalogEntity); /* update */
        }
    }
}
