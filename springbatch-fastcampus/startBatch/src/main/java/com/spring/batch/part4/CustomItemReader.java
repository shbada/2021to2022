package com.spring.batch.part4;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemReader 직접 생성하기
 * @param <T>
 */
public class CustomItemReader<T> implements ItemReader {
    private final List<T> items;

    public CustomItemReader(List<T> items) {
        this.items = new ArrayList<>(items);
    }

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (!items.isEmpty()) {
            return items.remove(0); /* 첫번째 아이템 반환과 동시에 제거 */
        }

        return null; /* null 리턴은 chunk 반복의 끝이라는 의미 : 더이상 처리할 데이터가 없다 */
    }
}
