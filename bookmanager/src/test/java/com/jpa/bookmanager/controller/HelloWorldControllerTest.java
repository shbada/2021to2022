package com.jpa.bookmanager.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @SpringBootTest 사용시 스프링 컨텍스트 전체 로딩
@SpringBootTest
//@WebMvcTest /* MvcTest 할 수 있도록 */
// JPA metamodel must not be empty! 에러 발생
// HelloWorldControllerTest 가 웹 MVC 테스트(슬라이스 테스트:전체 스프링 컨텍스트를 로딩하는게 아닌 웹 컨트롤러에 관한 일부만 로딩함))이기 때문
@MockBean(JpaMetamodelMappingContext.class) // JPA 관련이 필요 없기 때문에 로딩을 할 수 없었는데, 이를 있는것처럼 막음
class HelloWorldControllerTest {

    @Autowired
    private WebApplicationContext wac; // @SpringBootTest 일때

    @BeforeEach // @SpringBootTest 일때
    void before() { // MockMvc 를 직접 만들어준다.
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    //@Autowired // @SpringBootTest 일때
    private MockMvc mockMvc;

    @Test
    void helloWorld() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/init"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("hello world!"));
    }
}