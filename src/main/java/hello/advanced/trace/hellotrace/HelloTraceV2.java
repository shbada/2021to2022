package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component // 싱글톤으로 사용하기 위해 스프링 빈으로 등록
public class HelloTraceV2 {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    /**
     * 로그 시작할때 호출
     * @param message
     * @return
     */
    public TraceStatus begin(String message) {
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis(); // 시작 시간

        /* 로그 출력 */
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX,
                traceId.getLevel()), message);

        return new TraceStatus(traceId, startTimeMs, message);
    }

    /**
     * V2 추가
     * @param beforeTraceId
     * @param message
     * @return
     */
    public TraceStatus beginSync(TraceId beforeTraceId, String message) {
        // TraceId traceId = new TraceId();
        TraceId nextId = beforeTraceId.createNextId(); // transactionId 그대로, level + 1로
        Long startTimeMs = System.currentTimeMillis();

        log.info("[" + nextId.getId() + "] " + addSpace(START_PREFIX,
                nextId.getLevel()) + message);

        return new TraceStatus(nextId, startTimeMs, message);
    }

    /**
     * 로그 종료할때 호출
     * @param status
     */
    public void end(TraceStatus status) {
        complete(status, null);
    }

    /**
     * 예외 발생시 호출
     * @param status
     * @param e
     */
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();

        /* 경과 시간 */
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();

        TraceId traceId = status.getTraceId();

        if (e == null) { /* 정상의 경우 */
            log.info("[{}] {}{} time={}ms", traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(),
                    resultTimeMs);
        } else { /* 예외 존재할 경우 */
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
                    addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs,
                    e.toString());
        }
    }

    /**
     * level = 0
     * level = 1 | --->
     * level = 2 |     | --->
     *
     * (예외 발생시)
     * level 0:
     * level 1: |<X-
     * level 2: |   |<X-
     * @param prefix
     * @param level
     * @return
     */
    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < level; i++) {
            sb.append((i == level - 1) ? "|" + prefix : "|   ");
        }

        return sb.toString();
    }

    /**
     * 남은문제
     * 1) HTTP 요청을 구분하고 깊이를 표현하기 위해서 TraceId 동기화가 필요
     * 2) TraceId 의 동기화를 위해서 관련 메서드의 모든 파라미터를 수정
     * 3) 로그를 처음 시작할 때는 begin() 을 호출하고, 처음이 아닐때는 beginSync() 를 호출해야 한다.
     *
     * HTTP 요청을 구분하고 깊이를 표현하기 위해서 TraceId 를 파라미터로 넘기는 것 말고 다른 대안은 없을까?
     */
}
