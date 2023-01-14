package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FieldLogTraceTest {
    FieldLogTrace trace = new FieldLogTrace();

    /**
     [d3500ec4] hello1
     [d3500ec4] |-->hello2
     [d3500ec4] |<--hello2 time=3ms
     [d3500ec4] hello1 time=7ms
     */
    @Test
    void begin_end_level2() {
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.begin("hello2");
        trace.end(status2);
        trace.end(status1);
    }

    /**
     [42686b02] hello
     [42686b02] |-->hello2
     [42686b02] |<X-hello2 time=2ms ex=java.lang.IllegalStateException
     [42686b02] hello time=7ms ex=java.lang.IllegalStateException
     */
    @Test
    void begin_exception_level2() {
        // releaseTraceId():
        // releaseTraceId():
        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.begin("hello2");
        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}