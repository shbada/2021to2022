package com.jpa.bookmanager.service;

import com.jpa.bookmanager.domain.User;
import com.jpa.bookmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Service
public class UserService {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void put() {
        User user = new User();
        user.setName("newUser");
        user.setEmail("newUser@test.com");
        // 여기까지 User 는 아직 비영속 상태

        /** 영속화 상태가 되어, 영속성 컨텍스트의 관리 대상이 된다. */
        entityManager.persist(user); // 영속화

        // save 라는 액션 없이, update 쿼리가 실행되어 name 이 변경되었다.
        // 트랜잭션이 완료된 시점에 DB 데이터랑 싱크가 맞춰진다.
        user.setName("newUserAfterPersist");

        // userRepository.save(user); // 저장도 명시적으로 표현이 가능하다.
        // 해당 시점에 바로 저장이 되는게 아님.
        // 트랜잭션이 종료되는 시점(commit 시점)에 DB 데이터와 싱크 맞추기 위해 update 쿼리가 실행됨.

        /** 준 영속 상태(detach) : 원래 영속화 되었던 객체를 분리해서 영속성 컨텍스트 밖으로 꺼냄 */
        entityManager.detach(user); // 준영속성 상태로 변경
        // detach 말고 clear, close 메서드도 있는데, 이 또한 모두 준영속성 상태로 변경해준다. detach 보단, clear, close 가 좀더 강하다.
        // 이렇게 됬을 경우 위 save(user)의 update 가 실행되지 않았다.
        entityManager.merge(user); // 준영속성 상태 변경 후에는 명시적으로 수행시켜줘야 update 된다.

        // entityManager.flush(); // 얘를 또 추가하면, clear()를 타기 전에 commit 된다.
        // entityManager.clear(); // merge() 가 무시됨.

        /** 삭제 상태 : remove */
        // remove 하게되면 해당 Entity는 더이상 사용할 수 없는 상태가 된다.
        // entityManager.remove(user); // clear -> remove 하면 에러 발생.. (비영속성일땐 될수없음)

        User user1 = userRepository.findById(1L).get();
        entityManager.remove(user1);

        // delete query 실행
        userRepository.findAll().forEach(System.out::println); // 1번의 회원 데이터가 삭제됨을 확인할 수 있다.
        // 이 이후 부터는 user1 에 관련해서 어떤 수정을 발생시킬 수 없다.
    }
}
