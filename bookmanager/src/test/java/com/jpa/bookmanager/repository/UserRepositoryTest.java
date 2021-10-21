package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.Gender;
import com.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.endsWith;

@SpringBootTest
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    /**
     * enum
     */
    @Test
    void enumTest() {
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setGender(Gender.MALE);

        userRepository.save(user); // gender 들어감

        userRepository.findAll().forEach(System.out::println);

        System.out.println(userRepository.findRawRecord().get("gender")); // 0
    }

    /**
     * insertable, updatable 확인
     */
    @Test
    void insertAndUpdate() {
        User user = new User();
        user.setName("martin");
        user.setEmail("martin2@test.com");

        userRepository.save(user);

        User user2 = userRepository.findById(1L).orElseThrow(RuntimeException::new);

        user2.setName("marrrrtin");

        userRepository.save(user2);
    }

    /**
     * 테스트 데이터 삽입
     */
    @BeforeEach
    void insertTestData() {
        for (int i = 1; i < 6; i++) {
            User user = new User();
            user.setEmail("seohae" + i + "@naver.com");
            user.setName("test" + i);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            userRepository.save(user);
        }

        User user = new User();
        user.setEmail("seohaea" + "@naver.com");
        user.setName("test1");
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    /**
     * 페이징 처리
     */
    @Test
    void paging() {
        System.out.println("findByName : " + userRepository.findByName("test1"
                , PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))));
        // getContent
        System.out.println("findByName : " + userRepository.findByName("test1"
                , PageRequest.of(0, 1, Sort.by(Sort.Order.desc("id")))).getContent());
    }

    /**
     * 정렬
     */
    @Test
    void orderBy() {
        // TopN 중 N을 생략하면 1이 default 값이다
        System.out.println("findTopByNameOrderByIdDesc : " + userRepository.findTopByNameOrderByIdDesc("test1"));
        // 역순
        System.out.println("findTop1ByNameOrderByIdDesc : " + userRepository.findTop1ByNameOrderByIdDesc("test1"));
        // 정순
        System.out.println("findTop1ByNameOrderByIdAsc : " + userRepository.findTop1ByNameOrderByIdAsc("test1"));
        // id desc, email asc
        System.out.println("findFirstByNameOrderByIdDescEmailAsc : " + userRepository.findFirstByNameOrderByIdDescEmailAsc("test1"));

        // sort param (Sort.by(Sort.Order.desc("id"))안에는 컬럼명)
        System.out.println("findFirstByName With Sort Params : " + userRepository.findFirstByName("test1", Sort.by(Sort.Order.desc("id"))));
        System.out.println("findFirstByName With Sort Params : " + userRepository.findFirstByName("test1", Sort.by(Sort.Order.desc("id"), Sort.Order.asc("email"))));
        // getSort() 호출
        System.out.println("findFirstByName With Sort Params : " + userRepository.findFirstByName("test1", getSort()));
    }

    /* 가독성 */
    private Sort getSort() {
        return Sort.by(
                Sort.Order.desc("id"),
                Sort.Order.asc("email"),
                Sort.Order.desc("createdAt"),
                Sort.Order.desc("updatedAt")
        );
    }

    /**
     * 메소드명 가독성
     */
    @Test
    void findUserBy() {
        System.out.println("findUserByNameIs : " + userRepository.findUserByNameIs("test1"));
        System.out.println("findUserByName : " + userRepository.findUserByName("test1"));
        System.out.println("findUserByNameEquals : " + userRepository.findUserByNameEquals("test1"));
    }

    /**
     * Like
     */
    @Test
    void like() {
        System.out.println("findByNameStartingWith : " + userRepository.findByNameStartingWith("test1"));
        System.out.println("findByNameEndingWith : " + userRepository.findByNameEndingWith("test"));
        // like ? escape ?
        System.out.println("findByNameContains : " + userRepository.findByNameContains("test"));

        // % 추가 (like ? escape ?)
        System.out.println("findByNameLike : " + userRepository.findByNameLike("%test%"));
    }

    /**
     * In절
     */
    @Test
    void in() {
        System.out.println("findByNameIn : " + userRepository.findByNameIn(Lists.newArrayList("test1", "test2")));
    }

    /**
     * NULL 관련된 메서드 테스트
     */
    @Test
    void nullCheck() {
        // id is not null
        System.out.println("findByIdIsNotNull : " + userRepository.findByIdIsNotNull());

        // Collection type 의 not empty 를 체크함 (where~exist)
        // System.out.println("findByAddressIsNotEmpty : " + userRepository.findByAddressIsNotEmpty());
        //System.out.println("findByIdIsNotEmpty : " + userRepository.findByIdIsNotEmpty());
    }

    /**
     * findByCreatedAtBetween : created_at between ? and ?
     * findByIdBetween : id between ? and ? (양 끝의 조건도 포함, 그러므로 1L, 3L 이면 1~3)
     */
    @Test
    void between() {
        System.out.println("findByCreatedAtBetween : " +
                userRepository.findByCreatedAtBetween(LocalDateTime.now().minusDays(1L), LocalDateTime.now().plusDays(1L)));

        System.out.println("findByIdBetween : " + userRepository.findByIdBetween(1L, 3L));

        // = between (id>=? AND id<=?)
        System.out.println("findByIdGreaterThanEqualAndIdLessThanEqual : " + userRepository.findByIdGreaterThanEqualAndIdLessThanEqual(1L, 3L));
    }

    /**
     * findByCreatedAtGreaterThan : 아래 findByCreatedAtAfter 와 동일 (created_at>?)
     * findByCreatedAtGreaterThanEqual : created_at>=?
     */
    @Test
    void greaterThan() {
        System.out.println("findByCreatedAtGreaterThan : " + userRepository.findByCreatedAtGreaterThan(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtGreaterThanEqual : " + userRepository.findByCreatedAtGreaterThanEqual(LocalDateTime.now().minusDays(1L)));
    }

    /**
     * ID long 4 이상인 데이터 조회 (findByIdAfter) : id>?
     */
    @Test
    void idAfter() {
        System.out.println("findByIdAfter : " + userRepository.findByIdAfter(4L));
    }

    /**
     * findByAAfter : A 시간의 After (created_at>?)
     * findByABefore :  A 시간의 Before
     */
    @Test
    void timeAfterBefore() {
        System.out.println("findByCreatedAtAfter : " + userRepository.findByCreatedAtAfter(LocalDateTime.now().minusDays(1L)));
        System.out.println("findByCreatedAtBefore : " + userRepository.findByCreatedAtBefore(LocalDateTime.now().minusDays(1L)));
    }

    /**
     * findByAAndB : A AND B (WHERE AND 절)
     * findByAOrB : A OR B (WHERE OR절)
     */
    @Test
    void findByWhere() {
        System.out.println("findByEmailAndName : " + userRepository.findByEmailAndName("seohae1@naver.com", "test1"));
        System.out.println("findByEmailOrName : " + userRepository.findByEmailOrName("seohae1@naver.com", "test2"));
    }

    /**
     * select method 확인하기
     */
    @Test
    void selectMethod() {
        /* 모두 동일한 쿼리, 동일한 결과 */
        System.out.println("findByEmail : " + userRepository.findByEmail("seohae1@naver.com"));
        System.out.println("getByEmail : " + userRepository.getByEmail("seohae1@naver.com"));
        System.out.println("readByEmail : " + userRepository.readByEmail("seohae1@naver.com"));
        System.out.println("queryByEmail : " + userRepository.queryByEmail("seohae1@naver.com"));
        System.out.println("searchByEmail : " + userRepository.searchByEmail("seohae1@naver.com"));
        System.out.println("streamByEmail : " + userRepository.streamByEmail("seohae1@naver.com"));
        System.out.println("findUserByEmail : " + userRepository.findUserByEmail("seohae1@naver.com"));
        System.out.println("findSomeThingByEmail : " + userRepository.findSomeThingByEmail("seohae1@naver.com"));
        // System.out.println("findByByName : " + userRepository.findByByName("seohae1@naver.com")); // RuntimeError

        /* limit keyword (1) */
        System.out.println("findFirst1ByName : " + userRepository.findFirst1ByName("test1"));
        System.out.println("findTop1ByName : " + userRepository.findTop1ByName("test1"));


        /* limit 은 없음 (Last1 은 인식되지 않으므로 쿼리 동작에 limit 이 없다) */
        System.out.println("findLast1ByName : " + userRepository.findLast1ByName("test1"));
    }

    /**
     * name 을 파라미터로 조회
     */
    @Test
    void select() {
        System.out.println(userRepository.findByName("test1"));
        System.out.println(userRepository.findOptionalByName("test1"));
    }

    /**
     * update
     */
    @Test
    void update() {
        // insert query
        userRepository.save(new User("david", "david@test.com"));

        // select query
        User user = userRepository.findById(1L).orElseThrow(RuntimeException::new);
        user.setEmail("martin-update@test.com");

        // select-update
        userRepository.save(user);

        /** SimpleJpaRepository */
    }

    /**
     * ExampleMatcher 활용해보기
기    */
    @Test
    void crud_example() {
        /* query by example (Entity 를 Example 로 만들고, matcher 과 함께하여 쿼리를 만들기 */
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id") /* id 은 매칭하지 않겠다 */
                .withIgnorePaths("name") /* name 은 매칭하지 않겠다 */
                .withMatcher("email", endsWith()); // user0_.email like ? escape ?

        // Example.of (User probe:가짜 객체):파라미터
        Example<User> example = Example.of(new User("ma", "@naver.com"), matcher);

        userRepository.findAll(example).forEach(System.out::println);


        User user = new User();
        user.setEmail("naver");

        ExampleMatcher matcher2 = ExampleMatcher.matching()
                .withMatcher("email", contains()); /* 양방향 like 검색 */
        Example<User> example1 = Example.of(user, matcher2);

        userRepository.findAll(example1).forEach(System.out::println);
    }

    /**
     * 리스트 페이징
     */
    @Test
    void crud_paging() {
        // limit ? offset ?
        // 2페이지 데이터인 4, 5가 출력됨
        // zero-based page index (0부터 시작)
        // Page<User> users = userRepository.findAll(PageRequest.of(1, 3));
        Page<User> users = userRepository.findAll(PageRequest.of(0, 3));
        users.stream().forEach(System.out::println);

        System.out.println("page : " + users);
        System.out.println("totalElements : " + users.getTotalElements()); // 전체 레코드 수
        System.out.println("totalPages : " + users.getTotalPages()); // 1페이지:3개, 2페이지:2개
        System.out.println("numberOfElements : " + users.getNumberOfElements()); // 현재 가져온 레코드 수
        System.out.println("sort: " + users.getSort()); // UNSORTED
        System.out.println("size: " + users.getSize()); // 페이징할때 나누는 크기

        users.getContent().forEach(System.out::println);
    }

    /**
     * deleteInBatch, deleteAllInBatch
     */
    @Test
    void crud_deleteBatch() {
        /* select 없이 delete 는 한번만 실행되고 where 에 or 절로 id 매핑 */
        //userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L, 3L))); // select-delete

        /* delete from table */
        userRepository.deleteAllInBatch();
        userRepository.findAll().forEach(System.out::println);
    }

    /**
     * deleteAll
     */
    @Test
    void crud_deleteAll() {
        // userRepository.deleteAll(); // select-delete
        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 3L))); // select-delete
        userRepository.findAll().forEach(System.out::println);
    }

    /**
     * delete
     */
    @Test
    void delete() {
        /* select (findById) -> delete */
        //userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));

        long count= userRepository.count();
        System.out.println("count : " + count);

        /* select -> delete */
        userRepository.deleteById(1L);
    }


    /**
     * existsById
     */
    @Test
    void crud_exist() {
        /* count(*) 쿼리가 실행됨 */
        boolean exist = userRepository.existsById(1L);
        System.out.println("exist : " + exist );
    }

    /**
     * get count
     */
    @Test
    void crud_count() {
        long count= userRepository.count();

        System.out.println("count : " + count);
    }

    /**
     * flush
     */
    @Test
    void crud_flush() {
//        userRepository.save(new User("test", "sss@naver.com"));
//        userRepository.flush();

        /**
         * flush(): DB 반영 시점에 연관된 것 (로그로는 확인이 어렵다)
         */
        userRepository.saveAndFlush(new User("test", "sss@naver.com"));

        userRepository.findAll().forEach(System.out::println);
    }

    /**
     * findOne
     */
    @Test
    @Transactional // could not initialize proxy 에러 해결
    void crud_user_데이터등록_조회_findOne() { // create, read, update, delete
        // could not initialize proxy
        User user = userRepository.getOne(1L); // Lazy (getReference)

        System.out.println(user);
    }

    /**
     * findById
     */
    @Test
    void crud_user_데이터등록_조회_findId() { // create, read, update, delete
        // Optional
        Optional<User> userOptional = userRepository.findById(1L);
        System.out.println(userOptional);

        // 데이터가 없을 경우 null
        User user = userRepository.findById(1L).orElse(null);
        System.out.println(user);
    }

    /**
     * saveAll, findAll
     */
    @Test
    void crud_user_데이터등록_조회() { // create, read, update, delete
        User user1 = new User("jack", "jack@test.com");
        User user2 = new User("steve", "steve@test.com");

        // userRepository.save(user1);
        // userRepository.save(user2);
        userRepository.saveAll(Lists.newArrayList(user1, user2));

        List<User> users = userRepository.findAll();
        users.forEach(System.out::println);
    }

    /**
     * findAllById
     */
    @Test
    void crud_user_리스트조회() { // create, read, update, delete
        /** list 조회 */
        // List<User> users = userRepository.findAll();

        /** name 기준 역순 정렬 */
        // List<User> users = userRepository.findAll(Sort.by(Sort.Direction.DESC, "name"));

        /** 1, 3, 5 id 데이터 조회 */
        List<User> users = userRepository.findAllById(Lists.newArrayList(1L, 3L, 5L));
        users.forEach(System.out::println);
    }

    /**
     * save, findAll
     */
    @Test
    void crud() { // create, read, update, delete
        /* 저장 */
        userRepository.save(new User());

        /* 조회 */
        // System.out.println(userRepository.findAll());

        userRepository.findAll().forEach(System.out::println);

        for (User user : userRepository.findAll()) {
            System.out.println(user);
        }
    }
}