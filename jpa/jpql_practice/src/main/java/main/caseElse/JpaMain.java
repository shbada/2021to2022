package main.caseElse;

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

            String query = "select " +
                                  "case when m.age <= 10 then '학생요금' " +
                                  "     when m.age >= 60 then '경로요금' " +
                                  "     else '일반요금' " +
                                  "end " +
                           "from Member m ";

            List<String> queryResult = em.createQuery(query).getResultList();
            queryResult.forEach(System.out::println);

            /*
            coalesce : 1개씩 조회해서 null 이 아니면 반환
             */
            // username 이 null 이면 '이름 없는 회원' 반환
            String query2 = "select coalesce(m.username, '이름 없는 회원') from Member m";

            List<String> queryResult2 = em.createQuery(query2).getResultList();
            queryResult2.forEach(System.out::println);

            /*
            nullif : 두 값이 같으면 null, 다르면 첫번째 값 반환
             */
            // username 이 '관리자'이면 null 반환, '관리자'가 아니면 첫번째값 반환
            String query3 = "select nullif(m.username, '관리자') from Member m";

            List<String> queryResult3 = em.createQuery(query3).getResultList();
            queryResult3.forEach(System.out::println);

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
