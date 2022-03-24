package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {
    private final EntityManager em;

    /**
     * 상품 저장
     * @param item
     */
    public void save(Item item) {
        // item id 가 없으면 신규 등록
        if (item.getId() == null) {
            em.persist(item);
        } else { // item id 가 있으면 정보 변경 -> merge : update 와 비슷
            /**
             * merge 문
             * -> ItemService-updateItem 메소드의 코드 로직과 똑같다.
             *
             * itemId 를 셋팅 해서 find 조회로 찾아온 객체에 파라미터 데이터로 셋팅해준다 (변경감지로 변경)
             *
             * 여기서 중요한건 item (파라미터로 받아온 객체)는 영속성으로 안바뀌고,
             * merge 호출 결과를 새로 Item resultItem  = em.merge(item); 으로 담는다면 item 이 영속성 객체이다.
             * resultItem , item 은 같지 않다. item 은 여전히 준영속 상태, resultItem은 영속상태다.
             *
             * 병합은 조심해야한다.
             * 변경 감지 기능을 사용하면 원하는 속성만 선택해서 변경할 수 있지만, 모든 속성이 다 변경된다.
             * 병합시, 데이터가 없으면 null 로 업데이트 할 위험이 있다. (모든 필드를 교체한다.)
             *
             * book.price 가 null일 경우
             * book.setPrice(form.getPrice()); 이게 만약 없다면 price 필드가 null 로 update 된다는것을 조심하자.
             */
            em.merge(item); // merge 는 실무에서 쓸 일이 없다. (변경감지로 변경 > ItemService-updateItem)
        }
    }

    /**
     * 상품 단건 조회
     * @param id
     * @return
     */
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    /**
     * 전체 상품 조회
     * @return
     */
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
