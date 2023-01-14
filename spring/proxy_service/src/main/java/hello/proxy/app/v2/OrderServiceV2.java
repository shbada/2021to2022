package hello.proxy.app.v2;

public class OrderServiceV2 {
    private final OrderRepositoryV2 orderRepository;

    // final 이므로 기본생성자 불가능

    public OrderServiceV2(OrderRepositoryV2 orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
