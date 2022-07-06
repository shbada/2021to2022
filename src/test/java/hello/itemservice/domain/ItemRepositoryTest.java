package hello.itemservice.domain;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import hello.itemservice.repository.memory.MemoryItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
@SpringBootApplication 어노테이션을 찾는다.
위 어노테이션의 설정을 그대로 가져온다.
따라서 @Import(JdbcTemplate..) 도 동일하게 설정된다.

테스트용 전용 데이터베이스를 두는 것이 테스트할때 좋다.
롤백도 하는게 좋다. (DELETE 데이터보다 롤백이 더 좋다.)

- 테스트는 다른 테스트와 격리해야한다.
- 테스트는 반복해서 실행할 수 있어야한다.
 */
@SpringBootTest
class ItemRepositoryTest {

    /**
     * 인터페이스 구현체가 아닌 인터페이스를 테스트한다.
     * 향후 다른 구현체로 변경했을대 해당 구현체가 잘 동작하는지를 동일하게 해당 테스트코드를 재사용할 수 있다.
     */
    @Autowired
    ItemRepository itemRepository;

    @AfterEach
    void afterEach() {
        // MemoryItemRepository 의 경우 제한적으로 사용
        if (itemRepository instanceof MemoryItemRepository) {
            ((MemoryItemRepository) itemRepository).clearStore();
        }
    }

    /**
     * 상품 저장
     */
    @Test
    void save() {
        //given
        Item item = new Item("itemA", 10000, 10);

        //when
        Item savedItem = itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId()).get();
        assertThat(findItem).isEqualTo(savedItem);
    }

    /**
     * 상품 수정
     */
    @Test
    void updateItem() {
        //given
        Item item = new Item("item1", 10000, 10);
        Item savedItem = itemRepository.save(item);
        Long itemId = savedItem.getId();

        //when
        ItemUpdateDto updateParam = new ItemUpdateDto("item2", 20000, 30);
        itemRepository.update(itemId, updateParam);

        //then
        Item findItem = itemRepository.findById(itemId).get();
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
        assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
    }

    /**
     * 상품 조회
     */
    @Test
    void findItems() {
        //given
        Item item1 = new Item("itemA-1", 10000, 10);
        Item item2 = new Item("itemA-2", 20000, 20);
        Item item3 = new Item("itemB-1", 30000, 30);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        //둘 다 없음 검증
        test(null, null, item1, item2, item3);
        test("", null, item1, item2, item3);

        //itemName 검증
        test("itemA", null, item1, item2);
        test("temA", null, item1, item2);
        test("itemB", null, item3);

        //maxPrice 검증
        test(null, 10000, item1);

        //둘 다 있음 검증
        test("itemA", 10000, item1);
    }

    void test(String itemName, Integer maxPrice, Item... items) {
        List<Item> result = itemRepository.findAll(new ItemSearchCond(itemName, maxPrice));
        /* 순서까지 정상 매칭되어야한다. */
        assertThat(result).containsExactly(items);
    }
}
