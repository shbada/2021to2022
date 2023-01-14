package hello.itemservice.repository.v2;

import hello.itemservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 스프링이 자동으로 빈 등록해준다.
 */
public interface ItemRepositoryV2 extends JpaRepository<Item, Long> {
}
