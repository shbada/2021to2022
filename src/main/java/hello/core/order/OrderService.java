package hello.core.order;

public interface OrderService {
    /**
     * 주문 생성
     * 회원Id, 상품명, 상품가격 의 정보로 주문을 생성하여 주문 결과를 반환한다.
     * @param memberId
     * @param itemName
     * @param itemPrice
     * @return 주문 결과
     */
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
