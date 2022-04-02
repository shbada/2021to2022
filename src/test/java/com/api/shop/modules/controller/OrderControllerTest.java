package com.api.shop.modules.controller;

import com.api.shop.modules.entity.Member;
import com.api.shop.modules.entity.Order;
import com.api.shop.modules.form.MemberAddForm;
import com.api.shop.modules.repository.MemberRepository;
import com.api.shop.modules.repository.OrderRepository;
import com.api.shop.modules.service.MemberService;
import com.api.shop.modules.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    MemberService memberService;

    @DisplayName("주문 리스트 조회")
    @Test
    void getOrderList() {
        List<Order> orderList = orderRepository.findAll();
        orderList.forEach(System.out::println);
    }

    @DisplayName("회원의 주문 리스트 조회")
    @Test
    void getOrderListOfMember() {
        long idx = registerMember();

        List<Order> orderListOfMember = orderService.getOrderListOfMember(idx);
        orderListOfMember.forEach(System.out::println);
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
}