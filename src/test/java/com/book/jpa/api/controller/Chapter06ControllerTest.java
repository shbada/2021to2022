package com.book.jpa.api.controller;

import com.book.jpa.chapter06.D다대다.MemberERepository;
import com.book.jpa.chapter06.D다대다.ProductERepository;
import com.book.jpa.chapter06.D다대다.MemberE;
import com.book.jpa.chapter06.D다대다.ProductE;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class Chapter06ControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberERepository memberRepository;

    @Autowired
    ProductERepository productRepository;

    @DisplayName("")
    @Test
    void test_save() throws Exception {
        mockMvc.perform(post("/chapter06/member/save"))
                .andExpect(status().isOk())
        ;

        List<MemberE> members = memberRepository.findAll();
        members.forEach(System.out::println);

        MemberE memberE = memberRepository.getById(members.get(0).getId());
        List<ProductE> productEList = memberE.getProductEList(); // 객체 그래프 탐색

        for (ProductE productE : productEList) {
            System.out.println(productE.getName());
        }

        Assertions.assertThat(memberE.getProductEList().get(0).getId()).isEqualTo(1L);
    }
}