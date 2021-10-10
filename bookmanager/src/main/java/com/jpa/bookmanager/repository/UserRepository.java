package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

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
}
