package hello.itemservice.repository;

import hello.itemservice.domain.Item;

import java.util.List;
import java.util.Optional;

/**
 * ItemRepository 에서 ItemSearchCond, ItemUpdateDto 를 사용 하기 때문에
 * 패키지 구조를 같이 두었다.
 *
 * ItemService 랑 같은 패키지에 두면 ItemRepository에서 ItemSearchCond, ItemUpdateDto 를 사용해야해서
 * ItemService 의 관리 패키지에 의존성이 생기게된다.
 *
 * 그러므로 ItemRepository에서의 사용이 끝이므로 ItemRepository 패키지에 두도록한다.
 */
public interface ItemRepository {
    /**
     * 저장
     * @param item
     * @return
     */
    Item save(Item item);

    /**
     * 수정
     * @param itemId
     * @param updateParam
     */
    void update(Long itemId, ItemUpdateDto updateParam);

    /**
     * 단건 조회
     * @param id
     * @return
     */
    Optional<Item> findById(Long id);

    /**
     * 전체 조회 (검색조건)
     * @param cond
     * @return
     */
    List<Item> findAll(ItemSearchCond cond);

}
