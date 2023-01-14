package com.spring.junit.calculator.controller;

import com.spring.junit.calculator.component.Calculator;
import com.spring.junit.calculator.component.ICalculator;
import com.spring.junit.calculator.dto.CalculatorReqDto;
import com.spring.junit.calculator.dto.CalculatorResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CalculatorController {

    private final Calculator calculator;

    @GetMapping("/sum")
    public int sum(@RequestParam int x, @RequestParam int y) {
        // http://localhost:8080/api/sum?x=10&y=5
        return calculator.sum(x, y);
    }

    @PostMapping("/minus")
    public CalculatorResDto minus(@ModelAttribute CalculatorReqDto calculatorReqDto) {
        int result = calculator.minus(calculatorReqDto.getX(), calculatorReqDto.getY());

        CalculatorResDto calculatorResDto = new CalculatorResDto();
        calculatorResDto.setResult(result);
        calculatorResDto.setResponse(new CalculatorResDto.Body());

        return calculatorResDto;
    }
}
