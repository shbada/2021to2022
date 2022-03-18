package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

@Repository // @Component가 포함되어있어서 빈으로 등록됨
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;
    /*
    // @PersistenceContext 대신 @Autowired 사용이 가능하다.
    @PersistenceContext // 이게 있으면 스프링이 알아서 EntityManager 을 생성해서 주입해준다.
    private EntityManager em;
    */

    // 생성자 주입 가능 (
    /*
    public MemberRepository(EntityManager em) {
        this.em = em;
    }
    */

    /*
    @PersistenceUnit
    private EntityManagerFactory fc; // 직접 주입도 가능
    */

    /**
     * 저장
     * @param member
     */
    public void save(Member member) {
        em.persist(member);
    }

    /**
     * 단건 조회
     * @param id
     * @return
     */
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    /**
     * 리스트 조회
     * @return
     */
    public List<Member> findAll() {
        // JPQL 사용 (SQL 이랑은 좀 다르지만, 문법의 약간의 차이는 있고 기능적으로도 차이가 있다.)
        // SQL 은 테이블 대상의 쿼리라면 JPQL 은 Entity 대상의 쿼리
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    /**
     * 이름에 해당하는 회원 조회
     * @param name
     * @return
     */
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                    .setParameter("name", name)
                    .getResultList();
    }
}
