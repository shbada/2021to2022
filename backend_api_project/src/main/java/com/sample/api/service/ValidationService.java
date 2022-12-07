package com.sample.api.service;

import com.sample.api.dto.ValidationParamDto;
import com.sample.api.dto.validator.groups.Client;
import com.sample.api.dto.validator.groups.ClientA;
import com.sample.api.dto.validator.groups.ClientB;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@RequiredArgsConstructor
@Service
@Slf4j
@Validated
public class ValidationService {
    @Validated({ClientA.class})
    public void testClientA(@Valid ValidationParamDto validationParamDto) {
        log.info("testClientA");
    }

    @Validated({ClientB.class})
    public void testClientB(@Valid ValidationParamDto validationParamDto) {
        log.info("testClientB");
    }
}
