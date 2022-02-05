package com.book.jpa.chapter06.F다대다_한계극복.api;

import com.book.jpa.chapter06.F다대다_한계극복.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/f")
public class FController {
    private final MemberFRepository memberRepository;
    private final ProductFRepository productRepository;
    private final MemberProductRepository memberProductRepository;

    @PostMapping("/test")
    public String saveMember() {
        // 회원저장
        MemberF memberF = new MemberF();
        memberF.setId(1L);
        memberF.setUsername("회원1");
        memberRepository.save(memberF);

        // 상품저장
        ProductF productF = new ProductF();
        productF.setId(1L);
        productF.setName("상품1");
        productRepository.save(productF);

        // 회원상품 저장
        MemberProduct memberProduct = new MemberProduct();
        memberProduct.setMemberF(memberF);
        memberProduct.setProductF(productF);
        memberProduct.setOrderAmount(2);
        memberProductRepository.save(memberProduct);

        return "ok";
    }
}
