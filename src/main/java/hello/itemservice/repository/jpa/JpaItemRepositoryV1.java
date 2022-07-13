package hello.itemservice.repository.jpa;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
/*
@Repository가 있으면 JPA 에러 발생시 스프링 데이터 에러로 예외가 변환된다.

- @Repository가 붙은 클래스는 컴포넌트 스캔의 대상이 된다.
- @Repository가 붙은 클래스는 예외 변환 AOP의 적용 대상이 된다.
  -> 스프링과 JPA를 함께 사용하는 경우 스프링은 JPA 예외 변환기('PersistenceExceptionTranslator')를 등록한다.
  -> 예외 변환 AOP 프록시는 JPA 관련 예외가 발생하면 JPA 예외 변환기를 통해 예외를 스프링 데이터 접근 예외로 변환한다.

아래 2개 어노테이션(@Repository, @Transactional)
을 주석처리하면 해당 Repository 객체가 프록시 객체로 생성되지 않는다.
 */
@Repository
@Transactional // JPA에서는 데이터 변경시 필수다.
public class JpaItemRepositoryV1 implements ItemRepository {
    /* 이 객체를 사용하여 데이터를 저장-조회 등을 수행한다.
    * 내부에 Datasource를 가지고있다. */
    private final EntityManager em;

    public JpaItemRepositoryV1(EntityManager em) {
        this.em = em;
    }

    @Override
    public Item save(Item item) {
        em.persist(item);
        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        Item findItem = em.find(Item.class, itemId);

        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());

        // commit
    }

    @Override
    public Optional<Item> findById(Long id) {
        Item item = em.find(Item.class, id);

        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String jpql = "select i from Item i";

        Integer maxPrice = cond.getMaxPrice();
        String itemName = cond.getItemName();

        if (StringUtils.hasText(itemName) || maxPrice != null) {
            jpql += " where";
        }

        boolean andFlag = false;
        List<Object> param = new ArrayList<>();

        if (StringUtils.hasText(itemName)) {
            jpql += " i.itemName like concat('%',:itemName,'%')";
            param.add(itemName);

            andFlag = true;
        }

        if (maxPrice != null) {
            if (andFlag) {
                jpql += " and";
            }

            jpql += " i.price <= :maxPrice";

            param.add(maxPrice);
        }

        log.info("jpql={}", jpql);

        TypedQuery<Item> query = em.createQuery(jpql, Item.class);

        if (StringUtils.hasText(itemName)) {
            query.setParameter("itemName", itemName);
        }

        if (maxPrice != null) {
            query.setParameter("maxPrice", maxPrice);
        }

        return query.getResultList();
    }
}
