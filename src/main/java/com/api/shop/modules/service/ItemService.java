package com.api.shop.modules.service;

import com.api.shop.common.exception.BadRequestException;
import com.api.shop.modules.entity.Item;
import com.api.shop.modules.entity.Member;
import com.api.shop.modules.form.ItemAddForm;
import com.api.shop.modules.form.ItemUpdateForm;
import com.api.shop.modules.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ModelMapper modelMapper;
    private final ItemRepository itemRepository;

    /**
     * 상품 등록
     * @param itemAddForm
     * @return
     */
    public Long addItem(ItemAddForm itemAddForm) {
        isExistItem(itemAddForm.getItemName());

        // save member
        Item item = modelMapper.map(itemAddForm, Item.class);

        Item newItem = itemRepository.save(item);
        return newItem.getIdx();
    }

    /**
     * 상품 단건 조회
     * @param idx
     * @return
     */
    public Item getItem(long idx) {
        // save member
        Optional<Item> byId = itemRepository.findById(idx);

        if (byId.isEmpty()) {
            throw new BadRequestException("상품 정보가 존재하지 않습니다.");
        }

        return byId.get();
    }

    /**
     * 상품 정보 수정
     * @param itemUpdateForm
     */
    @Transactional
    public void updateItem(ItemUpdateForm itemUpdateForm) {
        isExistItem(itemUpdateForm.getItemName());

        Optional<Item> item = itemRepository.findById(itemUpdateForm.getIdx());

        if (item.isEmpty()) {
            throw new BadRequestException("존재하지 않는 회원입니다.");
        }

        item.get().updateItem(itemUpdateForm);
    }

    /**
     * 상품명 존재 여부
     * @param itemName
     */
    private void isExistItem(String itemName) {
        Optional<Item> item = itemRepository.findByItemName(itemName);

        if (!item.isEmpty()) {
            throw new BadRequestException("해당 상품 이름의 상품이 이미 존재합니다.");
        }
    }
}
