package com.sample.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.api.dto.BatchStepExecutionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BatchService {
    /* redis */
    private final RedisTemplate<String, String> redisTemplate;

    public List<BatchStepExecutionDto> getBatchReport() throws JsonProcessingException {
        /* 1) redis get value */
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        String value = vop.get("test");

        /* 2) string to List */
        return new ObjectMapper().readValue(value, new TypeReference<List<BatchStepExecutionDto>>(){});
    }
}
