package com.book.jpa.chapter06.F다대다_한계극복;

import com.book.jpa.chapter06.F다대다_한계극복.api.MemberFRepository;
import com.book.jpa.chapter06.F다대다_한계극복.api.MemberProductRepository;
import com.book.jpa.chapter06.F다대다_한계극복.api.ProductFRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@SpringBootTest
@AutoConfigureMockMvc
class FControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    MemberFRepository memberRepository;

    @Autowired
    ProductFRepository productRepository;

    @Autowired
    MemberProductRepository memberProductRepository;

    @DisplayName("")
    @Test
    void test_save() throws Exception {
        mockMvc.perform(post("/f/test"))
                .andExpect(status().isOk())
        ;

        // 기본 키 값 생성
        MemberProductId memberProductId = new MemberProductId();
        memberProductId.setMemberF(1L);
        memberProductId.setProductF(1L);

        Optional<MemberProduct> byId = memberProductRepository.findById(memberProductId);
        System.out.println(byId.get().getMemberF().getUsername());
        System.out.println(byId.get().getProductF().getName());
        System.out.println(byId.get().getOrderAmount());

    }
}