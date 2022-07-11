package hello.itemservice.repository.mybatis;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * Mybatis 매핑 XML을 호출해주는 Mapper 인터페이스
 * @Mapper 어노테이션 필수
 *
 * Mybatis 스프링 연동 모듈
 *
 * Mybatis 스프링 연동 모듈이 @Mapper인 인터페이스를 모두 찾는다.
 * 위 인터페이스를 기반으로 동적 프록시 객체를 생성한다.
 * 실제 객체를 만들어내서 이를 기반으로 스프링 빈으로 등록한다.
 */
@Mapper
public interface ItemMapper {
    void save(Item item);

    void update(@Param("id") Long id, @Param("updateParam") ItemUpdateDto updateDto);

    List<Item> findAll(ItemSearchCond itemSearchCond);

    Optional<Item> findById(Long id);
}
