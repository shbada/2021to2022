package com.test.technology.controllers;

import com.test.technology.commons.Output;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"RedisController"})
@RestController
@RequestMapping("/redis")
public class RedisController {

    private final RedisTemplate<String, String> redisTemplate;
    private final Output output;

    public RedisController(RedisTemplate<String, String> redisTemplate, Output output) {
        this.redisTemplate = redisTemplate;
        this.output = output;
    }

    @ApiOperation(value = "redis key 등록")
    @PostMapping("")
    public ResponseEntity<?> addRedisKey(@ApiParam(value = "key", defaultValue = "") @RequestParam String key,
                                         @ApiParam(value = "value", defaultValue = "") @RequestParam String value) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();

        vop.set(key, value);
        return output.send(vop.get(key));
    }

    @ApiOperation(value = "redis value 조회")
    @GetMapping("/{key}")
    public ResponseEntity<?> getRedisKey(@ApiParam(value = "key", defaultValue = "") @PathVariable String key) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();

        return output.send(String.valueOf(vop.get(key)));
    }
}
