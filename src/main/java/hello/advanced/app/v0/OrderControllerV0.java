package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 로그 추적기
 * 애플리케이션이 커지면서 모니터링/운영이 중요해지는 단계다.
 * 어떤 부분에서 병목이 발생하고, 예외가 발생하는지 로그를 통해 확인을 해야한다.
 * 로그를 미리 남겨둔다면 이런 부분을 손쉽게 찾을 수 있다.
 *
 * 로그를 남긴다고 해서, 비즈니스 로직의 동작에 영향을 주면 안된다.
 * 특정 ID를 남겨서 어떤 HTTP 요청에서 시작된것인지 명확하게 구분이 가능해야한다.
 * 트랜잭션 ID, 여기서는 하나의 HTTP 요청이 시작해서 끝날때 까지를 하나의 트랜잭션이라 한다.
 */
@RestController // (@Controller + @ResponseBody)
@RequiredArgsConstructor
public class OrderControllerV0 {

    private final OrderServiceV0 orderService;

    /**
     * localhost:8080/v0/request?itemId=hello
     * @param itemId
     * @return
     */
    @GetMapping("/v0/request")
    public String request(String itemId) {
        orderService.orderItem(itemId);
        return "ok";
    }
}
