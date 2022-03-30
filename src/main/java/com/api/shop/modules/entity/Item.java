package com.api.shop.modules.entity;

import com.api.shop.modules.form.ItemUpdateForm;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "idx")
    private Long idx;

    @Column(unique = true) // unique
    private String itemName;

    private int price;

    private int stockQuantity;

    /**
     * 상품 정보 수정
     * @param itemUpdateForm
     */
    public void updateItem(ItemUpdateForm itemUpdateForm) {
        this.itemName = itemUpdateForm.getItemName();
        this.price = itemUpdateForm.getPrice();
        this.stockQuantity = itemUpdateForm.getStockQuantity();
    }
}
