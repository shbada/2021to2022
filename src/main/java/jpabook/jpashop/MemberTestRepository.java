package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/** Respository : Entity를 찾아주는 역할 */
@Repository
public class MemberTestRepository {
    // EntityManager
    @PersistenceContext // SpringBoot 가 EntityManager 을 주입해준다. (springboot-starter-jpa)
    private EntityManager em;

    /**
     * 회원 데이터 저장
     * @param member
     * @return
     */
    public Long save(Member member) {
        em.persist(member);
        return member.getId();
    }

    /**
     * 회원 조회 (id)
     * @param id
     * @return
     */
    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
