package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain3 {
    public static void main(String[] args) {
        // hello : persistence.xml 의 name 값
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        tx.begin();

        try {
            Movie movie = new Movie();
            movie.setDirector("aaa");
            movie.setName("aaa");
            movie.setPrice(1200);
            movie.setActor("aaa");

            em.persist(movie); // item, movie 모두 들어가있다.

            em.flush();
            em.clear(); // 영속성 제거

            Movie findMovie = em.find(Movie.class, movie.getId()); // item과 조인되어 조회된다.

            // @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS) 이후
            Item item = em.find(Item.class, movie.getId()); // union all 쿼리로 데이터를 다찾는다. 성능..

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
