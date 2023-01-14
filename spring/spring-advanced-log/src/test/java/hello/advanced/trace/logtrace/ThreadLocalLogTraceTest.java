package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ThreadLocalLogTraceTest {
    ThreadLocalLogTrace trace = new ThreadLocalLogTrace();

    /**
     * [41c51dc6] hello1
     * [41c51dc6] |-->hello2
     * [41c51dc6] |<--hello2 time=2ms
     * [41c51dc6] hello1 time=7ms
     */
    @Test
    void begin_end_level2() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }

    /**
     * [9c0d8153] hello
     * [9c0d8153] |-->hello2
     * [9c0d8153] |<X-hello2 time=3ms ex=java.lang.IllegalStateException
     * [9c0d8153] hello time=7ms ex=java.lang.IllegalStateException
     */
    @Test
    void begin_exception_level2() {
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}