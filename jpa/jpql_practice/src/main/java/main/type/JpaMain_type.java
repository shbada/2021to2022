package main.type;

import jpql.Member;
import jpql.MemberType;

import javax.persistence.*;
import java.util.List;

public class JpaMain_type {
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

            // @DiscriminatorValue("BB")
            // DTYPE = 'BB'
            Query query = em.createQuery("select m from Member as m " +
                    "where type(m) = Member", Member.class); // MemberType 은 전체로 작성

            List<Object[]> resultList = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
