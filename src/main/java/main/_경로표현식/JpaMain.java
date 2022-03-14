package main._경로표현식;

import jpql.Member;
import jpql.Team;

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

            // m.username : 상태 필드 (단순히 값을 저장하기 위한 필드) - 경로 탐색의 끝 (m.username.?? 불가능)

            // m.team t : 단일 값 연관 필드 (@ManyToOne, @OneToOne 대상이 엔티티) - 묵시적 내부 조인(inner join) 발생 - 왠만하면 발생하게 짜지말자.

            // m.orders o : 컬렉션 값 연관 필드 (@OneToMany, @ManyToMany 대상이 컬렉션) - 묵시적 내부조인 발생 / 탐색 불가능 (t.members.?? 불가능, t.members.size만 가능)
                // -> 명시적 조인으로 하면 된다. "select m.username From Team t join t.members m"

            // 실무에서는 다 무시하고.. 묵시적 조인 쓰지말고 명시적 조인을 써라.
//            TypedQuery<Team> query = em.createQuery("select m.username from Member m join m.team t join m.orders o", Team.class);
//
//            List<Team> resultList = query.getResultList();
//            System.out.println("result.size = " + resultList.size());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
