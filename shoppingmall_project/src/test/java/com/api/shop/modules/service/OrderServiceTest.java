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
    void addOrder() {
        /* member 등록 */
        long idx = registerMember();

        /* item 등록 */
        ItemAddForm itemAddForm = new ItemAddForm();
        itemAddForm.setItemName("test1");
        itemAddForm.setPrice(1000);
        itemAddForm.setStockQuantity(100);

        Long itemIdx = itemService.addItem(itemAddForm);

        /* Order parameter setting */
        OrderAddForm orderAddForm = new OrderAddForm();
        orderAddForm.setMemberIdx((int) idx);
        orderAddForm.setItemIdx(Math.toIntExact(itemIdx));
        orderAddForm.setItemCount(1);
        orderAddForm.setAddressForm(new AddressForm("add1", "add2", "12345"));

        Long newOrderIdx = orderService.addOrder(orderAddForm);

        /* 방금 신규 등록된 주문 조회 */
        Order order = orderService.getOrder(newOrderIdx);

        /* check */
        Assertions.assertEquals(order.getIdx(), newOrderIdx);
    }

    @Test
    @DisplayName("주문 단건 조회 - 성공")
    void getItem() {
        /* 주문 등록 */
        this.addOrder();

        /* 주문 첫번째 idx 가져오기 */
        List<Order> orderList = orderRepository.findAll();
        Long orderIdx = orderList.get(0).getIdx();

        /* 주문 단건 조회 수행 */
        Order order = orderService.getOrder(orderIdx);

        /* check */
        Assertions.assertEquals(orderIdx, order.getIdx());
    }
}