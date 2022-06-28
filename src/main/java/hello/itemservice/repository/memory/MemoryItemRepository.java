package hello.itemservice.repository.memory;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class MemoryItemRepository implements ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); //static
    private static long sequence = 0L; //static

    @Override
    public Item save(Item item) {
        /* 저장시 시퀀스 증가 */
        item.setId(++sequence);

        /* store map에 저장 */
        store.put(item.getId(), item);

        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        /* item 조회 */
        Item findItem = findById(itemId).orElseThrow();

        /* update */
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    @Override
    public Optional<Item> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        /* store에 저장된 데이터 */
        return store.values().stream()
                .filter(item -> {
                    /* 상품명이 비어져있으면 검색하지 않았다는 것으로 통과 */
                    if (ObjectUtils.isEmpty(itemName)) {
                        return true;
                    }

                    /* 상품명에 해당하는 경우에 통과 */
                    return item.getItemName().contains(itemName);
                }).filter(item -> {
                    /* 상품 가격이 비어져있으면 검색하지 않았다는 것으로 통과 */
                    if (maxPrice == null) {
                        return true;
                    }

                    /* 상품 가격보다 작거나 같을 경우 통과 */
                    return item.getPrice() <= maxPrice;
                })
                .collect(Collectors.toList());
    }

    /**
     * 테스트를 할때마다 데이터가 중복되므로 임시로 데이터를 삭제하는 테스트 용도
     */
    public void clearStore() {
        store.clear();
    }

}
