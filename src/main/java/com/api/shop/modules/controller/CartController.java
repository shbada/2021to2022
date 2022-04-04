package com.api.shop.modules.controller;

import com.api.shop.common.Output;
import com.api.shop.modules.entity.Item;
import com.api.shop.modules.form.CartAddForm;
import com.api.shop.modules.form.ItemAddForm;
import com.api.shop.modules.form.ItemUpdateForm;
import com.api.shop.modules.repository.CartRepository;
import com.api.shop.modules.repository.ItemRepository;
import com.api.shop.modules.service.CartService;
import com.api.shop.modules.service.ItemService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"CartController"})
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartRepository cartRepository;
    private final CartService cartService;
    private final Output output;

    /**
     * 장바구니 리스트 조회
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> getCartList() {
        return output.send(cartRepository.findAll());
    }

    /**
     * 장바구니 등록
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> addCart(@Valid @ModelAttribute CartAddForm cartAddForm) {
        Long idx = cartService.addCart(cartAddForm);
        return output.send(idx);
    }
}
