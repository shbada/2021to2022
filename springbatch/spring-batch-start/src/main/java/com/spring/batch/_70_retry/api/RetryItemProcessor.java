package com.spring.batch._70_retry.api;

import com.spring.batch._70_retry.NoRetryException;
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
        if (item.equals("2") || item.equals("3")) {
            // 2, 3일때 실패되서 (2번 retry) 후에 4번은 정상적으로 수행되는게 아닌가? (아님)
            // 예외가 발생하게 되고 chunk의 처음 단계로 간다. - > item 이 0번째부터 다시 시작한다는 것이다.
            // 그럼 2번째는 다시 또 예외가 발생하게된다. (retry 두번째로 다시 시도)
            // 다시 chunk의 처음 단계로 가고, 다시 0번째부터 수행 -> 2번째에서 또 예외 발생.
            // 이 구문은 계속적으로 예외가 발생하는 구문이다.
            // 이런 상황에서는 skip, retry 를 같이 설정해야만 에러 발생 후 skip 하여 계속 진행시킬 수 있다.

            /** skip 설정 추가 - RetryConfiguration */

            // item0 성공, item1 성공 -> 이후 item2 처리시, 예외 발생
            // chunk 의 처음 단계로 이동 -> item0 부터 다시 시작
            // item2를 다시 만났다.
            // retryContext 를 통해서 스프링 배치가 재시도 가능 여부를 결정하고 여러가지 상태 정보를 가지고 활용한다.
            // retryContext 안에 count 가 재시도를 몇번 시도했는지, maxAttempts 가 최대 재시도 가능 횟수
            // 이런 정보가 있는데, item 마다 개별적으로 존재한다.
            // attributes 의 context.state 가 아이템 (2)
            // 그렇다면 item0, item1 의 각 context 안의 count 는 0일테고, 2는 에러가 한번 발생했으므로 1이다.
            // 이제 다시 에러가 발생해서 최대 가능 횟수 2에 도달했다.
            // 다시 chunk 처음으로 돌아가고, 다시 item2 로 왔다.
            // 여기서 이제 recoveryCallback 을 수행한다.
            // SimpleRetryExceptionHandler.java - recover() 에서 skip 을 체크한다.
            // shouldSkip()
            // 여기서 에러가 최대수만큼 발생했던 2를 skip 한다.
            // recover 의 skip 은 chunk 처음이 아닌, for 문으로 다시 돌아간다. (FaultTolerantChunkProcessor 의 for문)
            // 여기서 item 이 0, 1, 3, 4 가 실행중이였고 이제 '3'의 차례로 3을 수행한다.
            // 3은 다시 여기를 타면서 에러가 발생한다. (아이템마다 context 이므로 재시도 가능)
            // 3도 동일하게 2번 재시도 완료 후, recoveryCallback 이 타면서 shoudSkip() 을 탄다. -> skip된다.
            // 이후 4부터는 정상적으로 수행된다. (이때 완료 후, writer 에는 0, 1, 4 만 전달되겠다. )
            cnt++;
            throw new NoRetryException("Process failed. cnt:" + cnt);
        }

        return item;
    }
}
