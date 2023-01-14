package com.api.shop.modules.service;

import com.api.shop.modules.entity.Cart;
import com.api.shop.modules.entity.Order;
import com.api.shop.modules.form.CartAddForm;
import com.api.shop.modules.form.ItemAddForm;
import com.api.shop.modules.form.MemberAddForm;
import com.api.shop.modules.repository.CartRepository;
import com.api.shop.modules.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class CartServiceTest {
    @Autowired
    CartService cartService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberService memberService;

    @Autowired
    ItemService itemService;

    @DisplayName("장바구니 리스트 조회")
    @Test
    void getCartList() {
        List<Cart> cartList = cartRepository.findAll();
        cartList.forEach(System.out::println);
    }

    @Test
    @DisplayName("장바구니 등록 - 성공")
    void addCart() {
        CartAddForm cartAddForm = new CartAddForm();
        cartAddForm.setMemberIdx(registerMember());
        cartAddForm.setItemIdx(registerItem());
        cartAddForm.setCount(1);

        Long cartIdx = cartService.addCart(cartAddForm);

        Cart cart = cartRepository.findById(cartIdx).get();

        Assertions.assertEquals(cartIdx, cart.getIdx());
    }

    /**
     * 회원 등록
     * @return
     */
    private long registerMember() {
        MemberAddForm memberAddForm = new MemberAddForm();
        memberAddForm.setMemberName("kimseohae");
        memberAddForm.setPassword("1234512345");

        return memberService.saveMember(memberAddForm);
    }

    /**
     * 회원 등록
     * @return
     */
    private long registerItem() {
        ItemAddForm itemAddForm = new ItemAddForm();
        itemAddForm.setItemName("test1");
        itemAddForm.setPrice(1000);
        itemAddForm.setStockQuantity(100);

        return itemService.addItem(itemAddForm);
    }
}