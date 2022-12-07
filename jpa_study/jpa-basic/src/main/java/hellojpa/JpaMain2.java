package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain2 {
    public static void main(String[] args) {
        // hello : persistence.xml 의 name 값
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        tx.begin();

        try {
            // 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            // member.setTeam(team);
            // member.changeTeam(team); 양쪽에 다 있으면 문제 발생 (아래 addMember에서 해당 코드가 진행됨) -> 삭제하자.
            em.persist(member);

            // 순수 상태를 고려해서 항상 양쪽에 값을 설정해주자.
            // team.getMembers().add(member);
            team.addMember(member);

            em.flush();
            em.clear();

            Team findTeam = em.find(Team.class, team.getId()); // 1차캐
            List<Member> members = findTeam.getMembers();

            System.out.println("============");
            for (Member m : members) {
                System.out.println("m:" + m.getUsername());
            }
            System.out.println("============");

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
