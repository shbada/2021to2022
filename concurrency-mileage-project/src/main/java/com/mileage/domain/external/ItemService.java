package com.mileage.domain.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ItemService {
    public int callItemTest() {
        log.info("threadName : " + Thread.currentThread().getName());

        try {
            Thread.sleep(1000); // 1초 걸린다 가정
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return 1;
    }
}
