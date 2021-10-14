package com.jpa.bookmanager.repository;

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
    }

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

    @Test
    void crud_deleteBatch() {
        /* select 없이 delete 는 한번만 실행되고 where 에 or 절로 id 매핑 */
        //userRepository.deleteInBatch(userRepository.findAllById(Lists.newArrayList(1L, 3L))); // select-delete

        /* delete from table */
        userRepository.deleteAllInBatch();
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void crud_deleteAll() {
        // userRepository.deleteAll(); // select-delete
        userRepository.deleteAll(userRepository.findAllById(Lists.newArrayList(1L, 3L))); // select-delete
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void delete() {
        /* select (findById) -> delete */
        //userRepository.delete(userRepository.findById(1L).orElseThrow(RuntimeException::new));

        long count= userRepository.count();
        System.out.println("count : " + count);

        /* select -> delete */
        userRepository.deleteById(1L);
    }


    @Test
    void crud_exist() {
        /* count(*) 쿼리가 실행됨 */
        boolean exist = userRepository.existsById(1L);
        System.out.println("exist : " + exist );
    }

    @Test
    void crud_count() {
        long count= userRepository.count();

        System.out.println("count : " + count);
    }

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

    @Test
    @Transactional // could not initialize proxy 에러 해결
    void crud_user_데이터등록_조회_findOne() { // create, read, update, delete
        // could not initialize proxy
        User user = userRepository.getOne(1L); // Lazy (getReference)

        System.out.println(user);
    }

    @Test
    void crud_user_데이터등록_조회_findId() { // create, read, update, delete
        // Optional
        Optional<User> userOptional = userRepository.findById(1L);
        System.out.println(userOptional);

        // 데이터가 없을 경우 null
        User user = userRepository.findById(1L).orElse(null);
        System.out.println(user);
    }

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