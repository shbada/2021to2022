package com.api.shop.modules.controller;

import com.api.shop.common.Output;
import com.api.shop.modules.entity.Item;
import com.api.shop.modules.entity.Member;
import com.api.shop.modules.form.*;
import com.api.shop.modules.repository.ItemRepository;
import com.api.shop.modules.repository.MemberRepository;
import com.api.shop.modules.service.ItemService;
import com.api.shop.modules.service.MemberService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"ItemController"})
@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final Output output;

    /**
     * 상품 리스트 조회
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> getItemList() {
        return output.send(itemRepository.findAll());
    }

    /**
     * 상품 등록
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> addItem(@Valid @ModelAttribute ItemAddForm itemAddForm) {
        Long idx = itemService.addItem(itemAddForm);
        return output.send(idx);
    }

    /**
     * 상품 단건 조회
     * @return
     */
    @GetMapping("/{idx}")
    public ResponseEntity<?> getItem(@PathVariable long idx) {
        Item Item = itemService.getItem(idx);
        return output.send(Item);
    }

    /**
     * 상품 정보 변경
     * @param ItemUpdateForm
     * @return
     */
    @PutMapping("/")
    public ResponseEntity<?> putItem(ItemUpdateForm ItemUpdateForm) {
        itemService.updateItem(ItemUpdateForm);
        return output.send();
    }
}
