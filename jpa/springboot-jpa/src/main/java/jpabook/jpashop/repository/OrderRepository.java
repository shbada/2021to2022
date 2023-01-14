package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final EntityManager em;

    /**
     * 주문 저장
     * @param order
     */
    public void save(Order order) {
        em.persist(order);
    }

    /**
     * 주문 단건 조회
     * @param id
     * @return
     */
    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    /**
     * 주문 검색 조회
     * @param orderSearch
     * @return
     */
    public List<Order> findAll(OrderSearch orderSearch) {
        // 동적쿼리여야 한다면? 아래 메소드 참고
        /*return em.createQuery("select o from Order o join o.member m"
                + " where o.status = :status"
                + " and m.name like :name", Order.class)
                .setParameter("status", orderSearch.getOrderStatus())
                .setParameter("name", orderSearch.getMemberName())
                .setMaxResults(1000) // 최대 1000건
                .getResultList();*/

        return findAllByCriteria(orderSearch);

        /** 동적 쿼리는 'Querydsl'로 처리하자. */

    }

    /* 1번째 방법 (실무에서 사용X) */
    public List<Order> findAllString(OrderSearch orderSearch) {
        return getDynamicOrder(orderSearch);
    }

    /* 2번째 방법 (실무에서 사용X) */
    public List<Order> findAllByCriteria(OrderSearch orderSearch) {
        return getCriterOrder(orderSearch);

    }

    /** 얼마나 복잡한지 보여주기 위한 메소드 */
    private List<Order> getDynamicOrder(OrderSearch orderSearch) {
        /** 동적 쿼리 */
        //language=JPAQL
        String jpql = "select o From Order o join o.member m";
        boolean isFirstCondition = true;

        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.status = :status";
        }

        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " m.name like :name";
        }

        TypedQuery<Order> query = em.createQuery(jpql, Order.class).setMaxResults(1000); //최대 1000건

        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("status", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            query = query.setParameter("name", orderSearch.getMemberName());
        }

        return query.getResultList();
    }

    private List<Order> getCriterOrder(OrderSearch orderSearch) {
        /**
         * 단점 : 유지보수에 어렵다.
         */
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> o = cq.from(Order.class);
        Join<Order, Member> m = o.join("member", JoinType.INNER); //회원과 조인

        List<Predicate> criteria = new ArrayList<>();

        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            Predicate status = cb.equal(o.get("status"), orderSearch.getOrderStatus());
            criteria.add(status);
        }

        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getMemberName())) {
            Predicate name = cb.like(m.<String>get("name"), "%" + orderSearch.getMemberName() + "%");
            criteria.add(name);
        }

        cq.where(cb.and(criteria.toArray(new Predicate[criteria.size()])));
        TypedQuery<Order> query = em.createQuery(cq).setMaxResults(1000); //최대 1000건

        return query.getResultList();
    }
}
