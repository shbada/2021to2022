package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component // 싱글톤으로 사용하기 위해 스프링 빈으로 등록
public class HelloTraceV1 {
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
}
