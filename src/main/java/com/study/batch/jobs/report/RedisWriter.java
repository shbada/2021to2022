package com.study.batch.jobs.report;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.batch.entity.BatchStepExecution;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisWriter implements ItemWriter<BatchStepExecution> {
    /* redis */
    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public void write(List<? extends BatchStepExecution> list) throws Exception {
        log.info("@@@@@@@@@@@@@@@@@@@@@" +list);
        ValueOperations<String, String> vop = redisTemplate.opsForValue();

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(list);
        log.info("@@@@@@@@@@@@@@@@@@@@@" +jsonInString);

        vop.set("test", jsonInString);
    }
}
