package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {
    private static Map<Long, Member> store = new HashMap<>(); //static 사용
    private static long sequence = 0L; //static 사용

    /* 싱글톤 패턴 사용 (위 static 생략 가능..) */
    private static final MemberRepository instance = new MemberRepository();
    public static MemberRepository getInstance() {
        return instance;
    }

    private MemberRepository() {
    }

    /**
     * 저장
     * @param member
     * @return
     */
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    /**
     * 단건조회
     * @param id
     * @return
     */
    public Member findById(Long id) {
        return store.get(id);
    }

    /**
     * 리스트 조회
     * @return
     */
    public List<Member> findAll() {
        // store 원본 데이터 보호
        return new ArrayList<>(store.values());
    }

    /**
     * 전체삭제
     */
    public void clearStore() {
        store.clear();
    }
}
