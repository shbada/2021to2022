package com.jpa.bookmanager.repository;

import com.jpa.bookmanager.domain.Gender;
import com.jpa.bookmanager.domain.User;
import com.jpa.bookmanager.domain.UserHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserHistoryRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Test
    void userRelation() {
        /**
         * UserEntityListener 에서 User save 다음 UserHistory save 실행
         * preUpdate, prePersist -> postUpdate, postPersist 로 변경
         * 아래 userHistory userId = null 이  정상적으로 userId 나옴
         */
        User user = new User();
        user.setName("kimseohae");
        user.setEmail("test@naver.com");
        user.setGender(Gender.FEMALE);

        userRepository.save(user);

        user.setName("seohae");
        userRepository.save(user);

        user.setEmail("testtest@naver.com");
        userRepository.save(user);

        // userId = null -> userId
        // User.java 에 UserHist @OneToMany 추가 후 주석 가능
        // userHistoryRepository.findAll().forEach(System.out::println);
//        List<UserHistory> result = userHistoryRepository.findByUserId(
//                userRepository.findByEmail("testtest@naver.com").getId());

        // User.java 에 UserHist @OneToMany 추가 후 코드 변경
        List<UserHistory> result = userRepository.findByEmail("testtest@naver.com").getUserHistoryList();

        result.forEach(System.out::println);

        System.out.println("USerHistory.getUSer() : " + userHistoryRepository.findAll().get(0).getUser());
    }

    @Test
    void userHistoryTest() {
        User user = new User();
        user.setEmail("seohae777@naver.com");
        user.setName("test1");

        userRepository.save(user);

        user.setName("martin-new");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

    }

}