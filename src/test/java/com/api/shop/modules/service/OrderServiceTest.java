package com.api.shop.modules.service;

import com.api.shop.modules.entity.Item;
import com.api.shop.modules.entity.Order;
import com.api.shop.modules.form.AddressForm;
import com.api.shop.modules.form.ItemAddForm;
import com.api.shop.modules.form.MemberAddForm;
import com.api.shop.modules.form.OrderAddForm;
import com.api.shop.modules.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Autowired
    MemberService memberService;

    @Autowired
    ItemService itemService;

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

    @Test
    @DisplayName("주문 등록 - 성공")
    void addItem() {
        long idx = registerMember();

        ItemAddForm itemAddForm = new ItemAddForm();
        itemAddForm.setItemName("test1");
        itemAddForm.setPrice(1000);
        itemAddForm.setStockQuantity(100);

        Long itemIdx = itemService.addItem(itemAddForm);

        OrderAddForm orderAddForm = new OrderAddForm();
        orderAddForm.setMemberIdx((int) idx);
        orderAddForm.setItemIdx(Math.toIntExact(itemIdx));
        orderAddForm.setItemCount(1);
        orderAddForm.setAddressForm(new AddressForm("add1", "add2", "12345"));

        Long newOrderIdx = orderService.addOrder(orderAddForm);

        Optional<Order> byId = orderRepository.findById(newOrderIdx);

        Assertions.assertEquals(byId.get().getIdx(), newOrderIdx);
    }
}