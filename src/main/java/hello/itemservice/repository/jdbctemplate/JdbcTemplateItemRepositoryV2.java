package hello.itemservice.repository.jdbctemplate;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * NamedParameterJdbcTemplate
 *
 * SqlParameterSource
 * - BeanPropertySqlParameterSource
 *  - MapSqlParameterSource
 *
 *  map
 *
 *  BeanPropertyRowMapper
 */
@Slf4j
public class JdbcTemplateItemRepositoryV2 implements ItemRepository {
//    private final JdbcTemplate jdbcTemplate;

    /* 이름 기반 파라미터 매칭 */
    private final NamedParameterJdbcTemplate jdbcTemplate;

    public JdbcTemplateItemRepositoryV2(DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Item save(Item item) {
        String sql = "insert into item(item_name, price, quantity) values (:itemName, :price, :quanity)";

        // 필드명으로 위 파라미터명을 매칭한다.
        /*
            BeanPropertySqlParameterSource
            getXxx() -> xxx, getItemName() -> itemName
            get()으로 데이터를 자동으로 만들어낸다.
            key : itemName, value =
            key : price, value =

            항상 사용할수는 없다.
            update 의 경우 id가 dto에 없기 때문에.
            이때는 MapSqlParameterSource 나 Map을 사용하자.
         */
        SqlParameterSource param = new BeanPropertySqlParameterSource(item);

        // id 자동 생성 값
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(sql, param, keyHolder);

//        jdbcTemplate.update(connection -> {
//            // 자동 증가 키
//            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
//            ps.setString(1, item.getItemName());
//            ps.setInt(2, item.getPrice());
//            ps.setInt(3, item.getQuantity());
//            return ps;
//        }, keyHolder);

        // key
        long key = keyHolder.getKey().longValue();

        item.setId(key);

        return item;
    }

    @Override
    public void update(Long itemId, ItemUpdateDto updateParam) {
        String sql = "update item set item_name = :itemName, price = :price, quantity = :quantity where id = :id";

        // 위 save() 처럼 사용도 가능하고, 아래와같이 사용도 가능하다.
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("itemName", updateParam.getItemName())
                .addValue("price", updateParam.getPrice())
                .addValue("quantity", updateParam.getQuantity())
                .addValue("id", itemId);

        jdbcTemplate.update(sql, param);
    }

    @Override
    public Optional<Item> findById(Long id) {
        String sql = "select id, item_name, price, quantity from item where id = :id";

        try {
            Map<String, Long> param = Map.of("id", id);

            /* 결과 로우가 하나일 때 사용한다. */
            Item item = jdbcTemplate.queryForObject(sql, param, itemRowMapper());

            // 결과가 없으면 예외가 나옴
            return Optional.of(item);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Item> findAll(ItemSearchCond cond) {
        String itemName = cond.getItemName();
        Integer maxPrice = cond.getMaxPrice();

        SqlParameterSource param = new BeanPropertySqlParameterSource(cond);

        String sql = "select id, item_name, price, quantity from item";

        // 동적 쿼리 작성
        if (StringUtils.hasText(itemName) || maxPrice != null) {
            sql += " where";
        }

        boolean andFlag = false;

        if (StringUtils.hasText(itemName)) {
            sql += " item_name like concat('%', :itemName,'%')";
            andFlag = true;
        }

        if (maxPrice != null) {
            if (andFlag) {
                sql += " and";
            }
            sql += " price <= :maxPrice";
        }

        log.info("sql={}", sql); // 쿼리를 콘솔에 출력

        return jdbcTemplate.query(sql, param, itemRowMapper());
    }

    /**
     * 데이터베이스의 조회 결과를 객체로 변환할 때 사용한다.
     * JdbcTemplate이 다음과 같은 루프를 돌려주고, 개발자는 RowMapper 를
     * 구현해서 그 내부 코드만 채운다고 이해하면 된다.
     *
     * while(resultSet 이 끝날 때 까지) {
     *  rowMapper(rs, rowNum)
     * }
     * @return
     */
    private RowMapper<Item> itemRowMapper() {
//        return ((rs, rowNum) -> {
//            Item item = new Item();
//            item.setId(rs.getLong("id"));
//            item.setItemName(rs.getString("item_name"));
//            item.setQuantity(rs.getInt("quantity"));
//            item.setPrice(rs.getInt("price"));
//            return item;
//        });
        // Spring 제공 클래스
        /*
            BeanPropertyRowMapper
            ResultSet의 결과를 받아서 자바빈 규약에 맞추어 데이터를 변환한다.
            데이터베이스에서 조회한 결과 이름을 기반으로,
            setId()
            setPrice() 처럼 자바빈 프로퍼티 규약에 맞춘 메서드를 호출하는 것

            select id, item_name as itemName, price, quantity from item

            item_name 을 setItem_name이 없으므로 as 를 사용해서 별칭으로 매칭되게한다.
            ======>> 자동 변환이 사실 된다.

            언더스코어 표기법(snake_case) : item_name
            카멜 표기법(camelCase) : itemName

            언더스코어 표기법 -> 카멜로 자동 변환해준다.
            그래서 select item_name 으로 조회해도 setItemName()에 문제없이 값이 들어간다.

            결론
            snake_case는 자동으로 해결되니 그냥 두면 되고,
            컬럼 이름과 객체 이름이 완전히 다른 경우에는 조회 SQL에서 별칭(as)를 사용하면 된다.
         */
        return BeanPropertyRowMapper.newInstance(Item.class); // Camel 변환 지원
    }
}
