package com.api.shop.modules.controller;

import com.api.shop.common.Output;
import com.api.shop.modules.repository.OrderRepository;
import com.api.shop.modules.service.OrderService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"OrderController"})
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;
    private final OrderService orderService;
    private final Output output;

}
