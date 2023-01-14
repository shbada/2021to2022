package com.api.shop.modules.service;

import com.api.shop.common.exception.BadRequestException;
import com.api.shop.modules.entity.Item;
import com.api.shop.modules.form.ItemAddForm;
import com.api.shop.modules.form.ItemUpdateForm;
import com.api.shop.modules.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
//@TestPropertySource("classpath:test-application.yml")
class ItemServiceTest {
    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("상품 등록 - 성공")
    void addItem() {
        ItemAddForm itemAddForm = new ItemAddForm();
        itemAddForm.setItemName("test1");
        itemAddForm.setPrice(1000);
        itemAddForm.setStockQuantity(100);

        Long itemIdx = itemService.addItem(itemAddForm);

        Item byId = itemRepository.getById(itemIdx);
        Assertions.assertEquals(byId.getItemName(), itemAddForm.getItemName());
    }

    @Test
    @DisplayName("상품 등록 - 실패")
    void addItem_상품명_중복() {
        ItemAddForm itemAddForm = new ItemAddForm();
        itemAddForm.setItemName("test1");
        itemAddForm.setPrice(1000);
        itemAddForm.setStockQuantity(100);

        Long itemIdx1 = itemService.addItem(itemAddForm);

        ItemAddForm itemAddForm2 = new ItemAddForm();
        itemAddForm2.setItemName("test1");
        itemAddForm2.setPrice(1000);
        itemAddForm2.setStockQuantity(100);

        assertThrows(BadRequestException.class, () -> {
            itemService.addItem(itemAddForm2);
        });
    }

    @Test
    @DisplayName("상품 단건 조회 - 성공")
    void getItem() {
        ItemAddForm itemAddForm = new ItemAddForm();
        itemAddForm.setItemName("test1");
        itemAddForm.setPrice(1000);
        itemAddForm.setStockQuantity(100);

        Long itemIdx = itemService.addItem(itemAddForm);

        Item byId = itemService.getItem(itemIdx);

        Assertions.assertEquals(byId.getItemName(), itemAddForm.getItemName());
    }

    @DisplayName("상품 단건 조회 - 실패")
    @Test
    void update_member_fail() throws Exception {
        /* BadRequestException 발생 */
        assertThrows(BadRequestException.class, () -> {
            itemService.getItem(100000L);
        });
    }

    @Test
    @DisplayName("상품 수정 - 성공")
    void updateItem() {
        /* 신규 등록 */
        ItemAddForm itemAddForm = new ItemAddForm();
        itemAddForm.setItemName("test1");
        itemAddForm.setPrice(1000);
        itemAddForm.setStockQuantity(100);

        Long itemIdx = itemService.addItem(itemAddForm);

        /* 수정 */
        ItemUpdateForm itemUpdateForm = new ItemUpdateForm();
        itemUpdateForm.setIdx(itemIdx);
        itemUpdateForm.setItemName("update item");
        itemUpdateForm.setPrice(1000);
        itemUpdateForm.setStockQuantity(99);

        itemService.updateItem(itemUpdateForm);

        /* 조회 */
        Item byId = itemService.getItem(itemIdx);

        Assertions.assertNotEquals(byId.getItemName(), itemAddForm.getItemName()); // 불일치
        Assertions.assertEquals(byId.getItemName(), itemUpdateForm.getItemName()); // 일치
    }
}