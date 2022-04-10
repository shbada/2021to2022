package com.spring.batch.retry.api;

import com.spring.batch.retry.NoRetryException;
import org.springframework.batch.item.ItemProcessor;

public class RetryItemProcessor implements ItemProcessor<String, String> {

    private int cnt = 0;

    @Override
    public String process(String item) throws Exception {
        /*
        에러가 발생하면 chunk 의 처음으로 돌아간다.

        skip 과 retry 의 공통점
        - chunk 의 처음 단계로 돌아가고, itemReader 부터 아이템을 읽어서 처리한다.

        skip
        - 만약에 itemProcessor 에서 어떤 item 이 오류가 발생해서 처음으로 돌아간다면 itemReader 에서 아이템을 찾을때
        그 에러가 났던 아이템을 skip 하는 것이다. 데이터 자체는 이전과 동일할 수 없는것

        retry
        - 데이터의 변형/가공은 전혀 없다. 다시금 처음부터 시작한다는 의미로 동일한 item 을 다시 시도하는 것이다.
        그래서 에러가 났던 아이템을 건너뛰거나 그런점은 없고, 동일한 데이터로 재시도를 한다.

        ChunkOrientedTasklet 으로 돌아간다. (chunk 의 처음)


         */
        if(item.equals("2") || item.equals("3")) {
            cnt++;
            throw new NoRetryException("Process failed. cnt:" + cnt);
        }
        return item;
    }
}
