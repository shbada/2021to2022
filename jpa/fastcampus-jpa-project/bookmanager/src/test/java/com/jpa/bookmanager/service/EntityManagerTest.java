package com.jpa.bookmanager.service;

import com.jpa.bookmanager.domain.User;
import com.jpa.bookmanager.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@SpringBootTest
@Transactional
public class EntityManagerTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

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

    @Test
    void entityManagerTest() {
        /**
         * entityManager
         * userRepository.findAll() 과 같은 쿼리를 동작시켰음
         *
         * 쿼리메서드 : entityManager 을 직접적으로 사용하지 않도록 매핑하여 스프링이 제공하는것
         * hibernate 에서 entityManager 을 session 이라고 함.
         *
         * 우리가 쿼리메서드 save 호출 시점에 DB에 반영되지 않는다. (시점의 차이가 발생한다)
         */
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
    }

    @Test
    void cacheFindTest() {
        /* select 쿼리가 3번 실행 (우리가 직접 3번을 호출했기 때문에 3번이 호출될거라고 생각했음) */
        System.out.println(userRepository.findByEmail("seohae1@test.com"));
        System.out.println(userRepository.findByEmail("seohae1@test.com"));
        System.out.println(userRepository.findByEmail("seohae1@test.com"));


        /* select 쿼리 번 실행 */
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(2L).get());
        System.out.println(userRepository.findById(3L).get());

        /** 클래스 위에 @Treansaction 선언시, select 쿼리가 1번 실행되고, sout 3번이 모두 출력된다. */

        // 1번 호출 후, 그 이후부터는 캐싱처리된 데이터를 노출한다.

        /**
         * 1차 캐시 (key : id, value : 해당 entity)
         * id 로 조회시 캐싱 처리가 된다.
         * 1차 캐시 내에 데이터가 있으면 해당 데이터를 리턴해주고, 아니라면 DB에 직접 조회한 후 다시 1차 캐시에 저장한다.
         * findByEmail(email)은 그래서 캐싱처리가 안된다.
         */

        userRepository.deleteById(1L); // select -> delete 실행함
        // id 값으로 조회하는 케이스가 생겨서, 이럴땐 내부적으로 id 값으로 캐시가 자주 되기 때문에(JPA에서) 성능 저하를 방지한다.
        // 영속성 컨텍스트가 존재함으로써 @Transaction 이 존재하면 조회만 일어나고 delete 가 일어나지 않는다.
        // 이건 최대한 DB 반영이 느리기 때문.
        // 그래서 자체적으로 DB 반영 내용에 대해서만 쿼리가 실행되어 delete 쿼리가 실행되지않은건 최종 커밋이 안된것이다.
        // 테스트 이기 때문에 실제 DB로 쿼리 전달이 안되었다.


    }

    @Test
    void cacheFindTest2() {
        User user = userRepository.findById(1L).get();
        user.setName("aaaaaaa");

        userRepository.save(user);

        userRepository.flush(); // DB 반영 (여기에도 있으면 각 update 쿼리가 호출됨)

        System.out.println("--------------");

        user.setEmail("fasdasd@naver.com");

        // 트랜잭션으로 묶여있지않아서 update 쿼리가 2번 실행되었음
        // 각 save 의 transaction 으로 묶여있음. (상위에 없으면 내부에서만 묶음)


        userRepository.save(user);
        // 상위에 @Transaction 을 묶어주면 update 쿼리가 1번만 실행됨  (각각의 변경 내용을 가지고 있다가 merge 를 해서 한번에 업데이트)
        // 우리가 리스너에 등록했떤 user_history 도 1번만 실행됨

       //  userRepository.flush(); // DB 반영


        // 영속성 컨텍스트랑 실제 DB 데이터가 다름 (update 반영 전이지만, update 후의 데이터가 출력된다.)
        // 마치 update 된것처럼 보임
        // 영속성 컨텍스트의 값 - 실제 DB 데이터의 차이가 발생하는 순간
        System.out.println(">>>> 1 " + userRepository.findById(1L).get());
        userRepository.flush(); // update 쿼리 반영

        // DB의 select 쿼리를 날리지않고 영속성 캐싱 데이터를 출력한다.
        System.out.println(">>>> 2 " + userRepository.findById(1L).get());

        // 상위 @Transaction 이 있으면 묶음 트랜잭션 단위로 커밋됨 (모두 실행된 이후 커밋)
        // 그게 아니라면 각 메서드실행 마다 flush() 됨과 동일

        // 영속성 캐시에 있는 쿼리가 데이터베이스에 반영되는 경우는, ID 값이 아닌 JPQL 쿼리가 실행될 경우다.
        // Auto-flush
        // save(), save() -> 후에 repository.findAll() 이 마지막에 호출되면,
        // 쓰기지연이 발생하여 DB 에 실제로 데이터가 반영되지 않았고 영속성 컨텍스트에만 존재하는 상태인데,
        // DB에서 select * from user; 를 통해서 전체 user 를 가져오게되면. (findAll())
        // 영속성 컨텍스트 데이터와 DB 데이터 중에 더 최신값을 체크해서 가져온다. (데이터 비교가 발생한다)
        // 이런 merge 과정을 어떻게 해결할 것인가?
        // 쉬운 작업이 아니다. 이런 경우 영속성 컨텍스트의 값을 모두 flush()를 하여 DB에 반영 시키고,
        // DB 에 쿼리를 실행하여 해당 값을 영속성 컨텍스트로 가져온다.
    }
}
