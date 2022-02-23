package main.projection;

import jpql.Address;
import jpql.Member;
import jpql.Team;

import javax.persistence.*;
import java.util.List;

/**
 * 프로젝션 : SELECT 절에 조회할 대상을 지정하는것
 * 프로젝션 대상 : 엔티티, 임베디드 타입, 스칼라 타입(숫자, 문자 등 기본 데이터 타입)
 */
public class JpaMain_Address {
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

            // 임베디드 타입
            // address 는 Order 안의 컬럼이기 때문
            // from Address 는 불가능 Entity 가 아니므로
            TypedQuery<Address> query = em.createQuery("select o.address from Order as o", Address.class);

            List<Address> resultList = query.getResultList();

            tx.commit();;
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }
    }
}
