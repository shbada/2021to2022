package hello.advanced.trace;

import java.util.UUID;

public class TraceId {
    /* 두가지를 묶어서 TraceId 로 본다. */
    private String id; // 트랜잭션 ID
    private int level; // row level

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    /**
     * ID 생성
     * @return
     */
    private String createId() {
        // ab99e16f-3cde-4d24-8241-256108c203a2 : 생성된 UUID
        // ab99e16f : 앞 8자리만 사용
        return UUID.randomUUID().toString().substring(0, 8); // 너무 길다. 앞부분만 자르자.
    }

    /**
     * 다음 ID (id는 동일하고 level 이 증가)
     *
     * (예시)
     * [796bccd9] OrderController.request()
     *     [796bccd9] |-->OrderService.orderItem()
     *     [796bccd9] |   |-->OrderRepository.save()
     *     [796bccd9] |   |<--OrderRepository.save() time=1004ms
     * @return
     */
    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    /**
     * 이전 Level 의 로그 조회
     * id 는 기존과 같고, level 은 하나 감소
     * @return
     */
    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    /**
     * 첫번째 Level 여부 조회
     * @return
     */
    public boolean isFirstLevel() {
        return level == 0;
    }

    /* Getter */
    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
