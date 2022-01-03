package hello.advanced.hellotrace;

import static org.junit.jupiter.api.Assertions.*;
import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTraceV1Test {
    /**
     * [41bbb3b7] hello
     * [41bbb3b7] hello time=5ms
     */
    @Test
    void begin_end() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }

    /**
     * [898a3def] hello
     * [898a3def] hello time=13ms ex=java.lang.IllegalStateException
     */
    @Test
    void begin_exception() {
        HelloTraceV1 trace = new HelloTraceV1();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }
}