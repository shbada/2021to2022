package com.book.jpa.api.controller;

import com.book.jpa.api.repository.MemberERepository;
import com.book.jpa.api.repository.MemberRepository;
import com.book.jpa.api.repository.ProductERepository;
import com.book.jpa.api.repository.TeamRepository;
import com.book.jpa.api.service.Chapter05Service;
import com.book.jpa.chapter05.Member;
import com.book.jpa.chapter05.Team;
import com.book.jpa.chapter06.D다대다.MemberE;
import com.book.jpa.chapter06.D다대다.ProductE;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chapter06")
public class Chapter06Controller {
    private final MemberERepository memberRepository;
    private final ProductERepository productRepository;

    @PostMapping("/member/save")
    public String saveMember() {
        ProductE productA = new ProductE();
        productA.setId(1L);
        productA.setName("상품A");
        productRepository.save(productA);

        MemberE member1 = new MemberE();
        member1.setId(1L);
        member1.setUsername("회원1");
//        member1.getProductEList().add(productA); // 연관관계 설정
        member1.addProduct(productA); // 양방향 - 편의 메서드 추가 사용
        memberRepository.save(member1);

        return "ok";
    }
}
