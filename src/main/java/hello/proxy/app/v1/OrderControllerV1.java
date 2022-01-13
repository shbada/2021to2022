package hello.proxy.app.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping // 스프링은 @Controller 또는 @RequestMapping 이 있어야 스프링 컨트롤러로 인식할 수 있다.
@ResponseBody // HTTP 메시지 컨버터를 사용해서 응답한다.
public interface OrderControllerV1 {
    /**
     * LogTrace 적용할 대상
     * @param itemId
     * @return
     */
    @GetMapping("/v1/request")
    String request(@RequestParam("itemId") String itemId); // 인터페이스는 itemId 이름 명시 해주는게 좋다.

    /**
     * LogTrace 적용하지 않을 대상
     * @return
     */
    @GetMapping("/v1/no-log")
    String noLog();
}
