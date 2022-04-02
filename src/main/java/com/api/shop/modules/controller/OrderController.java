package com.api.shop.modules.controller;

import com.api.shop.common.Output;
import com.api.shop.modules.form.ItemAddForm;
import com.api.shop.modules.form.OrderAddForm;
import com.api.shop.modules.repository.OrderRepository;
import com.api.shop.modules.service.OrderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"OrderController"})
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final Output output;

    /**
     * 주문 리스트 조회
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<?> getOrderList() {
        return output.send(orderRepository.findAll());
    }

    /**
     * 주문 등록
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> addOrder(@Valid @ModelAttribute OrderAddForm orderAddForm) {
        Long idx = orderService.addOrder(orderAddForm);
        return output.send(idx);
    }

    /**
     * 회원의 주문 리스트 조회
     * @param memberIdx
     * @return
     */
    @GetMapping("/{memberIdx}")
    public ResponseEntity<?> getOrderListOfMember(@PathVariable long memberIdx) {
        return output.send(orderService.getOrderListOfMember(memberIdx));
    }
}
