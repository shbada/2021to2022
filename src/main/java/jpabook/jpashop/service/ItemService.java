package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 트랜잭션 필수
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    /**
     * 상품 저장
     * @param item
     */
    @Transactional // 우선권 1위
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    /**
     * updateItem 메소드에서 호출하는 준영속 엔티티의 변경감지 메소드 (merge 보다 변경감지 메서드를 사용해야한다.)
     * merge()와 같이 Item 을 리턴해주는 방식
     * @param itemId
     * @param bookParam
     */
    @Transactional // 우선권 1위
    public Item updateItem(Long itemId, Book bookParam) {
        Item findItem = itemRepository.findOne(itemId);

        // set 메소드로 데이터 지정하기 보다는 아래처럼 메소드를 만들어서 호출하자.
        // 추적하기가 좋다.
        // findItem.change(price, name, stockQuantity);

        /* 변경감지 사용 가능 - save() 호출 필요 없다 */
        findItem.setName(bookParam.getName());
        findItem.setPrice(bookParam.getPrice());
        findItem.setStockQuantity(bookParam.getStockQuantity());

        return findItem;
    }

    @Transactional // 우선권 1위
    public void updateItem2(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);

        // set 메소드로 데이터 지정하기 보다는 아래처럼 메소드를 만들어서 호출하자.
        // findItem.change(price, name, stockQuantity);

        /* 변경감지 사용 가능 */
        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    /**
     * 전체 상품 조회
     * @return
     */
    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    /**
     * 상품 단건 조회
     * @param itemId
     * @return
     */
    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
