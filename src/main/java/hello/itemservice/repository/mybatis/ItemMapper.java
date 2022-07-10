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
 */
@Mapper
public interface ItemMapper {
    void save(Item item);

    void update(@Param("id") Long id, @Param("updateParam") ItemUpdateDto updateDto);

    List<Item> findAll(ItemSearchCond itemSearchCond);

    Optional<Item> findById(Long id);
}
