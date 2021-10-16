package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

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
}
