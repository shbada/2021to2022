package main.paging;

import jpql.Member;

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
            member.setAge(10);

            em.persist(member);

            // as m 필수
            TypedQuery<Member> query = em.createQuery("select m from Member as m order by m.age desc", Member.class)
                    .setFirstResult(0) // 0 : limit ? // 0 이상 : limit ? offset ?
                    .setMaxResults(10);

            List<Member> resultList = query.getResultList();
            System.out.println("result.size = " + resultList.size());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
