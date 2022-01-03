package hello.advanced.trace;

/**
 * 로그의 상태정보
 * : 로그의 시작시간으로 걸린 시간 계산 가능
 *
 *
 */
public class TraceStatus {
    private TraceId traceId; // 내부에 트랜잭션ID와 level
    private Long startTimeMs; // 로그 시작시간
    private String message; // 시작시 사용한 메시지

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
