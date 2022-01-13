package hello.proxy.app.v2;

import org.springframework.web.bind.annotation.GetMapping;

public class OrderControllerV2 {
    private final OrderServiceV2 orderService;

    public OrderControllerV2(OrderServiceV2 orderService) {
        this.orderService = orderService;
    }

    /**
     * LogTrace 적용할 대상
     * @param itemId
     * @return
     */
    @GetMapping("/v2/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    /**
     * LogTrace 적용하지 않을 대상
     * @return
     */
    @GetMapping("/v2/no-log")
    public String noLog() {
        return "ok";
    }
}
