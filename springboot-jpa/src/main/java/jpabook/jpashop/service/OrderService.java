package jpabook.jpashop.service;

import jpabook.jpashop.domain.*;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // 트랜잭션 필수
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문 저장
     * @param memberId
     * @param itemId
     * @param count
     * @return
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {
        /*
           비즈니스 로직이 엔티티 안에 있다.
           -> 도메인 모델 패턴
           우리가 기존에 개발하던 서비스 계층에서 대부분의 비즈니스 로직을 처리하는 것
           -> 트랜잭션 스크립트 패턴
         */
        // 엔티티 조회
        /**
         * 다른 쪽에서 member, item 을 조회해서 가져오면 같은 트랜잭션이 아니기 때문에
         * 영속성에서 벗어나게된다.
         * 해당 Order 메소드의 트랜잭션의 영속성 관계에 관계 없는 객체이다.
         * 조회는 상관없지만 등록/수정을 실행할때에는 중요한 문제이다.
         */
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        // 배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        // 주문상품 생성
        // 이 방법 외의 다른 방법으로 생성하는걸 막으면 좋다. (생성자를 protected 로 선언하자.)
        OrderItem orderItem = OrderItem. createOrderItem(item, item.getPrice(), count);

        // 주문 생성
        Order order = Order.createOrder(member, delivery, orderItem);

        // 주문 저장
        /* 이거 하나로 각 엔티티 save 호출 없이, delibery, orderItem 모두 들어간다.
                -> Order.class 안에 CascadeType.ALL 로 셋팅해줬기 때문이다
           delivery, orderItem 은 order 에서만 사용한다. 그러므로 CascadeType.ALL 을 쓴 것이고,
           만약 다른 엔티티에서도 참조를 하는 경우에는 Cascade 를 쓰면 위험하다. */
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     * @param orderId
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        // 주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        // 주문 취소
        // update 쿼리를 날릴 필요 없이 entity 가 set 이 됨으로써 JPA 가 데이터를 변경해준다.
        order.cancel();
    }

    /**
     * 주문 리스트 조회
     * @param orderSearch
     * @return
     */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAll(orderSearch);
    }
}
