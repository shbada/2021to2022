package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

/**
 * WAS 는 쓰레드풀에서 쓰레드를 재사용한다.
 *
 * ThreadLocal.remove()를 꼭 해야한다. 상황으로 이해하기.
 * 1) thread-A가 동작한다.
 * 2) thread-A 전용보관소 쓰레드로컬에 데이터를 저장한다.
 * 3) thread-A 수행이 종료되어 thread pool에 반환한다.
 * 4) 쓰레드 생성 비용은 비싸기 때문에 쓰레드를 제거하지 않고 쓰레드풀에 만들어놓고 쓰레드를 재사용한다.
 * 5) thread-A가 쓰레드풀에 아직 살아있다. 쓰레드 로컬의 thread-A 전용 보관소에 데이터도 함께 살아있게된다.
 * 6) 다른 사용자가 새로운 요청을 했다.
 * 7) 쓰레드풀에서 쓰레드 하나가 thread-A 였다.
 * 8) thread-A 전용보관소에 이미 이전 요청이였던 데이터가 들어있는데, 이 값이 반환된다.
 * 9) 전혀 다른 사용자의 정보를 조회하게된다. (remove() 호출이 없을때 문제 발생)
 * 10) 각 요청이 끝날때 반드시 쓰레드 로컬의 값을 ThreadLocal.remove() 를 통해서 꼭 제거해야한다. 꼭.
 */
@Slf4j
public class ThreadLocalLogTrace implements LogTrace {
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private ThreadLocal<TraceId> traceIdHolder = new ThreadLocal<>(); // traceId 동기화, 동시성 이슈 발생

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceIdHolder.get();

        Long startTimeMs = System.currentTimeMillis(); // 시작 시간

        /* 로그 출력 */
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX,
                traceId.getLevel()), message);

        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId() {
        TraceId traceId = traceIdHolder.get();

        if (traceId == null) { // 존재하지 않으면 생성
            traceIdHolder.set(new TraceId());
        } else { // 존재하면 LEVEL 발번
            traceIdHolder.set(traceId.createNextId());
        }
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
        TraceId traceId = traceIdHolder.get();

        /* 하나의 요청이 종료하면 null 처리 */
        if (traceId.isFirstLevel()) {
            /**
             * 주의
             * 해당 쓰레드가 쓰레드 로컬을 모두 사용하고 나면 ThreadLocal.remove() 를 호출해서
             * 쓰레드 로컬에 저장된 값을 제거해주어야 한다.
             */
            traceIdHolder.remove(); // destroy (threadLocal 이 쓰레드가 저장한 데이터가 모두 삭제된다.)
        } else { // 이전 LEVEL (들어왔다가 나올때 LEVEL 하나씩 줄어들겠다)
            traceIdHolder.set(traceId.createPreviousId());
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
