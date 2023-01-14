package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain5 {
    public static void main(String[] args) {
        // hello : persistence.xml 의 name 값
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        tx.begin();

        try {
            Address address = new Address();

            /* Member2 */
            Member2 member2 = new Member2();

            Member2 member3 = new Member2();

            //member2, member3가 같은 address 공유

            // member2만 변경하려고했는데 아래가 실행되면 member1, member2 둘다 바뀐다.
            // 그렇게 하고싶으면 각자의 객체를 생성할것.
            // member2.getHomeAdress().setCity();


        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
