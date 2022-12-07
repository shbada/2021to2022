package hello.proxy.app.v3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderControllerV3 {
    private final OrderServiceV3 orderService;

    public OrderControllerV3(OrderServiceV3 orderService) {
        this.orderService = orderService;
    }

    /**
     * LogTrace 적용할 대상
     * @param itemId
     * @return
     */
    @GetMapping("/v3/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }

    /**
     * LogTrace 적용하지 않을 대상
     * @return
     */
    @GetMapping("/v3/no-log")
    public String noLog() {
        return "ok";
    }
}
