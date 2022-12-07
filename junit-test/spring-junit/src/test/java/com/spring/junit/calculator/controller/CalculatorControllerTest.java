package com.spring.junit.calculator.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.junit.calculator.component.Calculator;
import com.spring.junit.calculator.component.DollarCalculator;
import com.spring.junit.calculator.component.ICalculator;
import com.spring.junit.calculator.component.MarketApi;
import com.spring.junit.calculator.dto.CalculatorReqDto;
import com.spring.junit.calculator.dto.CalculatorResDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.bind.annotation.GetMapping;

import static org.junit.jupiter.api.Assertions.*;

/* 어떤 컨트롤러를 테스트 할것인지 명시 (Web 에 특화된 빈만) - 자원 낭비를 줄일 수 있다. 아까 @SpringBootTest 는 모든 빈이 등록되므로 자원소비가 됨 */
@WebMvcTest(CalculatorController.class)
@AutoConfigureWebMvc
@Import({Calculator.class, DollarCalculator.class})
class CalculatorControllerTest {

    @MockBean
    private MarketApi marketApi;

    /* 테스트 실행 전 실행 */
    @BeforeEach
    public void init() {
        Mockito.when(marketApi.connect())
                .thenReturn(3000);
    }

    @Autowired
    private MockMvc mockMvc;

    @Test
    void sumTest() throws Exception {
        // http://localhost:8080/api/sum?x=10&y=5

        mockMvc.perform(
                MockMvcRequestBuilders.get("http://localhost:8080/api/sum")
                        .queryParam("x", "10")
                        .queryParam("y", "10")
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                MockMvcResultMatchers.content().string("60000")
        ).andDo(
                MockMvcResultHandlers.print() /* 내용 출력 */
        );
    }

    @Test
    void minusTest() throws Exception {
        // http://localhost:8080/api/minus

        CalculatorReqDto calculatorReqDto = new CalculatorReqDto();
        calculatorReqDto.setX(10);
        calculatorReqDto.setY(10);

        /* dto -> json 변환 */
        String json = new ObjectMapper().writeValueAsString(calculatorReqDto);

        mockMvc.perform(
                MockMvcRequestBuilders.post("http://localhost:8080/api/minus")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        ).andExpect(
                // {"result":0,"response":{"resultCode":"OK"}}
                MockMvcResultMatchers.jsonPath("$.result").value("0")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.response.resultCode").value("OK")
        ).andDo(
                MockMvcResultHandlers.print() /* 내용 출력 */
        );
    }
}