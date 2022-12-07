package main.projection;

import jpql.Address;
import jpql.Member;
import jpql.MemberDto;

import javax.persistence.*;
import java.util.List;

/**
 * 프로젝션 : SELECT 절에 조회할 대상을 지정하는것
 * 프로젝션 대상 : 엔티티, 임베디드 타입, 스칼라 타입(숫자, 문자 등 기본 데이터 타입)
 */
public class JpaMain_Scala {
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

            // 스칼라타입
            // 각 필드별을 어떻게 매핑?

            // 1) Type
            Query query = em.createQuery("select m.username, m.age from Member m", Member.class);

            List resultList = query.getResultList();

            Object o = resultList.get(0);
            Object[] result = (Object[]) o;
            System.out.println("username = " + result[0]);
            System.out.println("age = " + result[1]);

            // 2) List<Object[]>
            List<Object[]> resultList2 = em.createQuery("select m.username, m.age from Member m")
                    .getResultList();

            // 3) 제일 깔끔한 방법 = new 명령어로 조회
            List<MemberDto> resultList3 = em.createQuery("select new jpql.MemberDto(m.username, m.age) from Member m", MemberDto.class)
                    .getResultList();

            System.out.println(resultList3.get(0).getUsername());

            tx.commit();;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
