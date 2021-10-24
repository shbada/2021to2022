package hello.core.member;

import java.util.HashMap;
import java.util.Map;

/**
 * 구현체 클래스
 */
public class MemoryMemberRepository implements MemberRepository {

    /* 동시성 이슈 ConcurrentHashMap 를 사용해야하지만 개발 용도이므로 HashMap 을 사용 */
    private static Map<Long, Member> store = new HashMap<>();

    /**
     * Member 저장
     * @param member
     */
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    /**
     * Member 단건 조회
     * @param memberId
     * @return
     */
    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
