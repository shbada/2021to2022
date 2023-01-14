package main._페치조인;

import jpql.Member;
import jpql.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain_한계 {
    public static void main(String[] args) {
        // hello : persistence.xml 의 name 값
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        tx.begin();

        try {
            Team teamA = new Team();
            teamA.setName("tesmA");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("tesmA");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("test1");
            member1.setTeam(teamA);
            member1.setAge(10);

            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("test2");
            member2.setTeam(teamA);
            member2.setAge(20);

            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("test3");
            member3.setTeam(teamB);
            member3.setAge(30);

            em.persist(member3);

            /**
             * 페치 조인 한계
             * - 1) 페치 조인 대상에 별칭을 줄 수 없다. (하이버네이트는 가능하지만 가급적 사용하지 말자)
             * // String fetchJpql = "select m from Member m join fetch m.team as t1 where t1.name..."; -> X
             * // 컬렉션 t1 안에서 그 중 1명만 불러온다..? 프로세스 자체에 누락 위험성 존재, 이상하게 동작함
             * // m.getTeams 하게되면 쿼리에서 조회된 1명만 나온다.. 이건 JPA에서 원한 객체가 아니다.
             * // 정합성 이슈 (데이터가 다 나올거라고 가정했는데 다 안나온다..)
             *
             * - 2) 둘 이상의 컬렉션은 페치 조인 할 수 없다.
             * // 정합성 이슈 (데이터가 다 나올거라고 가정했는데 다 안나온다..)
             * // 일대다대다.. 잘못하면 데이터가 예상치못한 결과가 나오게된다.
             *
             * - 3) 컬렉션을 페치 조인하면 페이징 API (setFirstResult, setMaxResults)를 사용할 수 없다.
             * // 일대일, 다대일 같은 단일 값 연관 필드들은 페치 조인해도 페이징이 가능하다.
             *   // -> 데이터 뻥튀기가 안됨
             *   // -> 일대다 이런건 데이터 뻥튀기가됨. 페이징 처리가 되면 데이터가 이상해진다.
             * // 하이버네이트는 경고 로그를 남기고 메모리에서 페이징(매우 위험하다)
             * // (해결방안) 일대다, 다대다 쿼리를 일대일, 다대일 등으로 바꿔서 수행한다. (방향 뒤집기)
             * // (해결방안) select t from Team t  (fetch Join을 뺀다.) -> 그 이후로 LAZY 로딩으로 사용시마다 쿼리 호출
             *   // -> 성능상으로 이슈가 생길 수 있음 (team 이 여러개면.. LAZY JOIN 여러개 가 나오겠다)
             *   // -> 그래서 이때 @BatchSize(100) 를 줄 수 있다. (Entity Team Members 필드 위에)
             *          // -> WHERE m.team_id in (?, ?) : members 는 LAZY 로딩 상태인데, lazy 를 가져올때
             *          //    한번에 IN 쿼리로 100개씩 넘긴다. (묶어서 쿼리를 수행시킨다)
             *          // 옵션으로도 가능함 (hibernate.default_batch_fetch_size)
             */
            // 페치 조인 (항상 우선적으로)
            // 쿼리가 한번에 나간다.
            String fetchJpql = "select m from Member m join fetch m.team";
            List<Member> fetchResult = em.createQuery(fetchJpql, Member.class).getResultList();

            for (Member member : fetchResult) {
                // 한방 쿼리로 조회됬으므로 모두 진짜 객체다.
                System.out.println("member = " + member.getUsername() + ", " + member.getTeam().getName());
            }

            /**
             * 여러 테이블을 조인해서 엔티티가 가진 모양이 아닌 전혀 다른 결과를 내야하면, 페치 조인 보다는 일반 조인을 사용하고
             * 필요한 데이터들만 조회해서 DTO로 반환하는 것이 효과적이다.
             *
             * 모든 것을 페치 조인으로 해결할 수는 없다.
             * 페치 조인은 객체 그래프를 유지할때 사용하면 효과적이다.
            */

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
