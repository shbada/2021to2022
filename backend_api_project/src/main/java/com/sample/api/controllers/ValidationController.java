package com.sample.api.controllers;

import com.sample.api.commons.Output;
import com.sample.api.dto.ValidationParamDto;
import com.sample.api.dto.validator.TestValidator;
import com.sample.api.service.ValidationService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"ValidationController"})
@RestController
@RequestMapping("/validation")
@RequiredArgsConstructor
@Slf4j
public class ValidationController {
    private final Output output;
    private final ValidationService validationService;
    private final TestValidator testValidator;

//    @InitBinder("validationParamDto")
//    public void initBinder(WebDataBinder webDataBinder) {
//        webDataBinder.addValidators(testValidator);
//    }

    /**
     * test
     * @param validationParamDto
     * @param bindingResult 주석
     * @return
     */
    @PostMapping(value = "/test")
    public ResponseEntity<?> test(@ModelAttribute ValidationParamDto validationParamDto) {
//        testValidator.validate(validationParamDto, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            log.info("validation error...");
//        }
//        validationService.testClientA(validationParamDto);
        validationService.testClientB(validationParamDto);
        return output.send(validationParamDto);
    }
}
