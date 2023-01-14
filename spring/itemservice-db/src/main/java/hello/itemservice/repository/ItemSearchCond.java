package hello.itemservice.repository;

import lombok.Data;

/**
 * 리스트 검색 조건
 * cond (condition 줄임말)
 */
@Data
public class ItemSearchCond {

    private String itemName;
    private Integer maxPrice;

    public ItemSearchCond() {
    }

    public ItemSearchCond(String itemName, Integer maxPrice) {
        this.itemName = itemName;
        this.maxPrice = maxPrice;
    }
}
