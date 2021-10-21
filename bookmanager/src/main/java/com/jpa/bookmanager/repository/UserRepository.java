package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/* <User, Long> : <Entity 타입, User 의 PK 타입> */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * extends PagingAndSortingRepository<T, ID>, QueryByExampleExecutor<T>
     * PagingAndSortingRepository의 findAll(Pageable pageable)을 권장
     * QueryByExampleExecutor (Example<S> example 을 파라미터로 받는 다양한 메서드 존재)
     *
     * findAll() : 성능 문제로 잘 사용하지 않는다. 대량 데이터를 모두 가져오는거엔 부하가 따른다.
     * deleteInBatch() : Entity 를 Iterable 타입으로 여러개 데이터를 받아서 지운다.
     * deleteAllInBatch() : 데이터를 통째로 지운다.
     * getOne() : id 값을 가지고 데이터 1개를 조회한다.
     * findAll(Example<S> example) 등
     */

    /**
     * 쿼리메서드는 returnType 은 정의한대로 맞추어 반환해준다.
     * 런타임시에 발생할 수 있는 에러 주의할것
     * @param name
     * @return
     */
    /* name 을 파라미터로 조회하는 메서드 */
    List<User> findByName(String name);
    Set<User> findUserByNameIs(String name);
    Set<User> findUserByName(String name);
    Set<User> findUserByNameEquals(String name);

    Optional<User> findOptionalByName(String name);

    /* select : 모두 동일한 쿼리, 동일한 결과 */
    User findByEmail(String email);
    User getByEmail(String email);
    User readByEmail(String email);
    User queryByEmail(String email);
    User searchByEmail(String email);
    User streamByEmail(String email);
    User findUserByEmail(String email);
    User findSomeThingByEmail(String email); // something 이라고 한다면? 조회가 잘됨 (결과도 같음)
    // RuntimeError
    // User findByByName(String email);

    /* limit keyword (1) first 뒤 숫자가 limit) */
    User findFirst1ByName(String name); /* name 의 결과가 2명일 경우 1개만 (first 뒤 숫자가 limit) */
    User findTop1ByName(String name); /* name 의 결과가 2명일 경우 1개만 */

    // User findLast1ByName(String name); // query did not return a unique result: 2
    /* limit 은 없음 (Last1 은 인식되지 않으므로 쿼리 동작에 limit 이 없다) */
    List<User> findLast1ByName(String name);

    /* WHERE AND */
    List<User> findByEmailAndName(String email, String name);
    /* WHERE OR */
    List<User> findByEmailOrName(String email, String name);

    /* TIME After, Before */
    List<User> findByCreatedAtAfter(LocalDateTime yesterday);
    List<User> findByCreatedAtBefore(LocalDateTime yesterday);

    List<User> findByIdAfter(long id);

    /* 위 findByCreatedAtAfter 와 동일 (created_at>?) */
    List<User> findByCreatedAtGreaterThan(LocalDateTime yesterday);
    List<User> findByCreatedAtGreaterThanEqual(LocalDateTime yesterday);

    /* between */
    List<User> findByCreatedAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
    List<User> findByIdBetween(long startId, long endId);

    // = between (id>=? AND id<=?) : findByIdBetween 와 동일하게 구현됨
    List<User> findByIdGreaterThanEqualAndIdLessThanEqual(Long id1, Long id2);

    /* ID 가 NULL 이 아닌 데이터 조회 */
    List<User> findByIdIsNotNull(); // id is not null
    // name is not null and name != '' 가 아님!!! 오해하지말자.
    // List<User> findByAddressIsNotEmpty(); // Collection type 의 not empty 를 체크함

    // in */
    List<User> findByNameIn(List<String> names);
    /* like */
    List<User> findByNameStartingWith(String name);
    List<User> findByNameEndingWith(String name);
    List<User> findByNameContains(String name);
    List<User> findByNameLike(String name);

    /* limit, order */
    List<User> findTopByNameOrderByIdDesc(String name); // TopN 중 N을 생략하면 1이 default 값이다
    List<User> findTop1ByNameOrderByIdDesc(String name); // 역순
    List<User> findTop1ByNameOrderByIdAsc(String name); // 정순
    List<User> findFirstByNameOrderByIdDescEmailAsc(String name); // id desc, email asc
    /* 정렬 조건을 Sort domain 으로 받는다 */
    List<User> findFirstByName(String name, Sort sort);

    /* paging */
    Page<User> findByName(String name, Pageable pageable);

    @Query(value = "select * from user limit 1;", nativeQuery = true)
    Map<String, Object> findRawRecord();
}
