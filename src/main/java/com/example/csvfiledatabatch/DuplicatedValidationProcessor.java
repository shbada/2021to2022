package com.example.csvfiledatabatch;

import org.springframework.batch.item.ItemProcessor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class DuplicatedValidationProcessor<T> implements ItemProcessor<T, T> {
    private final Map<String, Object> keyPool = new ConcurrentHashMap<>();
    private final Function<T, String> keyExtractor;
    private final boolean allowDuplicate;

    public DuplicatedValidationProcessor(Function<T, String> keyExtractor, boolean allowDuplicate) {
        this.keyExtractor = keyExtractor;
        this.allowDuplicate = allowDuplicate;
    }


    @Override
    public T process(T item) throws Exception {
        if (allowDuplicate) { // 필터링을 하지 않겠다는 의미로 item 그대로 return
            return item;
        }

        /* 해당 item 으로 추출하겠다 */
        String key = keyExtractor.apply(item);

        if (keyPool.containsKey(key)) {
            // 중복
            return null;
        }

        // 중복이 아닌 경우는 put
        keyPool.put(key, key);

        return item;
    }
}
