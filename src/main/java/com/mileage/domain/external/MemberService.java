package com.mileage.domain.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MemberService {
    public int callMemberTest() {
        log.info("threadName : " + Thread.currentThread().getName());

        try {
            Thread.sleep(2000); // 2초 걸린다 가정
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return 1;
    }
}
