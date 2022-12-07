package hello.itemservice.domain;

import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import hello.itemservice.repository.memory.MemoryItemRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

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

/*
   스프링이 제공하는 @Transactional 는 로직이 성공적으로 수행되면 커밋하도록 동작한다.
   이 어노테이션은 테스트에서 동작하면 예외적으로 동작한다.
   테스트에 있으면 스프링은 테스트를 트랜잭션 안에서 실행하고, 테스트가 끝나면 트랜잭션을 자동으로 롤백한다.

   위치가 클래스명 위면 모든 메서드에 적용되고, 메서드 위에 두면 해당 메서드만 적용된다.

   - 트랜잭션 시작
   로직 수행
   - 트렌잭션 강제로 롤백

   테스트 케이스나 클래스에 @Transactional 을 붙였을때만 이렇게 동작한다.
   Service, Repository 에도 @Transactional이 있을때 다 다른 트랜잭션으로 동작하느냐?
   -> 트랜잭션이 테스트에서 시작하기 때문에 테스트에서 시작한 트랜잭션에 참여하게된다. (기존 트랜잭션으로 그대로 이어진다.)
   같은 트랜잭션 범위에 모든 코드가 들어간다고 이해하자.
 */
@Transactional
class ItemRepositoryTest {

    /**
     * 인터페이스 구현체가 아닌 인터페이스를 테스트한다.
     * 향후 다른 구현체로 변경했을대 해당 구현체가 잘 동작하는지를 동일하게 해당 테스트코드를 재사용할 수 있다.
     */
    @Autowired
    ItemRepository itemRepository;

    /**
     * 스프링이 제공해주는 JdbcTransactionManager
     * PlatformTransactionManager 가 나중에 나온거라 이거 쓰면 된다.
     * PlatformTransactionManager 를 JdbcTransactionManager가 구현함
     */
//    @Autowired
//    PlatformTransactionManager transactionManager;
//
//    TransactionStatus transactionStatus;
//
//    @BeforeEach
//    void beforeEach() {
//        // 트랜잭션 시작
//        transactionStatus = transactionManager.getTransaction(new DefaultTransactionDefinition());
//    }

    @AfterEach
    void afterEach() {
        // MemoryItemRepository 의 경우 제한적으로 사용
        if (itemRepository instanceof MemoryItemRepository) {
            ((MemoryItemRepository) itemRepository).clearStore();
        }

//        /** 롤백 수행 */
//        transactionManager.rollback(transactionStatus);
    }

    /**
     * 상품 저장
     */
    @Test
    @Transactional
    @Commit /* 해당 메서드는 커밋을 수행하겠다는 의미 */
//    @Rollback(value = false)
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
