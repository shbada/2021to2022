package main.type;

import jpql.Member;
import jpql.MemberType;

import javax.persistence.*;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // hello : persistence.xml 의 name 값
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("kimseohae");
            member.setType(MemberType.ADMIN);
            member.setAge(10);

            em.persist(member);

            Query query = em.createQuery("select m.username, 'HELLO', TRUE from Member as m " +
                    "where m.type = jpql.MemberType.ADMIN"); // MemberType 은 전체로 작성

            // teamA
            // HELLO
            // true
            List<Object[]> resultList = query.getResultList();

            List<Object[]> list2 = em.createQuery("select m.username, 'HELLO', TRUE from Member as m " +
                    "where m.type = :userType")
                    .setParameter("userType", MemberType.ADMIN)
                    .getResultList(); // MemberType 은 전체로 작성

            list2.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
