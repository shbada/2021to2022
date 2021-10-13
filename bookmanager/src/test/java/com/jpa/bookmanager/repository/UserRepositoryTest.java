package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.User;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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