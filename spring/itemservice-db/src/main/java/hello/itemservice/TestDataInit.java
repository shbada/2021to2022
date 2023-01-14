package hello.itemservice;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@Slf4j
@RequiredArgsConstructor
public class TestDataInit {

    private final ItemRepository itemRepository;

    /**
     * 확인용 초기 데이터 추가
     * 애플리케이션을 실행할때 초기 데이터를 저장한다.
     * 메모리므로, 서버가 종료되면 데이터가 제거된다.
     *
     * Event Listener
     * - ApplicationReadyEvent
     * Spring이 실행 준비가 되었을때 발생하는 이벤트다.
     * AOP를 포함한 스프링 컨테이너가 완전히 초기화된 이후에 호출된다.
     * @PostConstruct 를 사용해도 되는데, 이건 AOP 같은 부분이 아직 처리되지 않은 시점에 호출될 수도 있어서 적용이 안될 수도 있다.
     */
    @EventListener(ApplicationReadyEvent.class) /* event listener */
    public void initData() {
        log.info("test data init");
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }

}
