package hello.advanced.hellotrace;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTraceV2;
import org.junit.jupiter.api.Test;

class HelloTraceV2Test {
    /**
     * [7be34a9d] hello1
     * [7be34a9d] |-->hello2
     * [7be34a9d] |<--hello2 time=15ms
     * [7be34a9d] hello1 time=20ms
     */
    @Test
    void begin_end_level2() {
        HelloTraceV2 trace = new HelloTraceV2();

        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        trace.end(status2);
        trace.end(status1);
    }

    /**
     * [80956fae] hello
     * [80956fae] |-->hello2
     * [80956fae] |<X-hello2 time=15ms ex=java.lang.IllegalStateException
     * [80956fae] hello time=19ms ex=java.lang.IllegalStateException
     */
    @Test
    void begin_exception_level2() {
        HelloTraceV2 trace = new HelloTraceV2();

        TraceStatus status1 = trace.begin("hello");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");

        trace.exception(status2, new IllegalStateException());
        trace.exception(status1, new IllegalStateException());
    }
}