package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

/**
 [c80f5dbb] OrderController.request() //syncTraceId(): 최초 호출 level=0
 [c80f5dbb] |-->OrderService.orderItem() //syncTraceId(): 직전 로그 있음 level=1 증가
 [c80f5dbb] | |-->OrderRepository.save() //syncTraceId(): 직전 로그 있음 level=2 증가
 [c80f5dbb] | |<--OrderRepository.save() time=1005ms //releaseTraceId():   level=2->1 감소

 [c80f5dbb] |<--OrderService.orderItem() time=1014ms // level=1->0 감소
 [c80f5dbb] OrderController.request() time=1017ms // level==0, traceId 제거
 */
@Slf4j
public class FieldLogTrace implements LogTrace {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private TraceId traceIdHolder; // traceId 동기화, 동시성 이슈 발생

    private void syncTraceId() {
        if (traceIdHolder == null) { // 존재하지 않으면 생성
            traceIdHolder = new TraceId();
        } else { // 존재하면 LEVEL 발번
            traceIdHolder = traceIdHolder.createNextId();
        }
    }

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();

        TraceId traceId = traceIdHolder;
        Long startTimeMs = System.currentTimeMillis(); // 시작 시간

        /* 로그 출력 */
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX,
                traceId.getLevel()), message);

        return new TraceStatus(traceId, startTimeMs, message);
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
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

        releaseTraceId();
    }

    private void releaseTraceId() {
        /* 하나의 요청이 종료하면 null 처리 */
        if (traceIdHolder.isFirstLevel()) {
            traceIdHolder = null; // destroy
        } else { // 이전 LEVEL (들어왔다가 나올때 LEVEL 하나씩 줄어들겠다)
            traceIdHolder = traceIdHolder.createPreviousId();
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
