package com.spring.batch._70_retry.template;

import com.spring.batch._70_retry.RetryableException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.classify.BinaryExceptionClassifier;
import org.springframework.classify.Classifier;
import org.springframework.retry.RecoveryCallback;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.support.DefaultRetryState;
import org.springframework.retry.support.RetryTemplate;

public class RetryItemProcessor2 implements ItemProcessor<String, Customer> {

    /** 우리가 만든 RetryTemplate */
    @Autowired
    private RetryTemplate retryTemplate;

    @Override
    public Customer process(String item) throws Exception {

        // 해당 예외에 대해서 롤백을 할 것인지 설정 - 예외 종류에 따라서 true, false 를 선택하는 클래스
        // default 는 true (재시도에 실패하면 무조건 롤백하겠다)
        Classifier<Throwable, Boolean> rollbackClassifier = new BinaryExceptionClassifier(true);

        /*
         * 우리가 만든 코드로 수행된다.
         * retryCallback, recoveryCallback
         * retry 가 발생하면 chunk 의 처음 단계로 간다고 했다.
         * 근데 아래 doWithRetry() 에서는 chunk 의 처음 단계로 가지 않는다.
         * 왜?
         * RetryTemplate.java > shouldRethrow() -> 다시 예외를 던지겠느냐? 라는 메서드가 있다.
         * 여기서 true 여야 chunk 처음으로 돌아가는 로직을 타는데, 지금은 false 다.
         * 위 메서드에서 체크하는 state 가 null 이다.
         * (api/패키지의 코드의 state 는 스프링배치가 처음부터 retryTemplate 을 통해서 state 에 default 를 만들어줬었기 때문에 null 이 아니였다.)
         * 우리가 아래 코드에서 인자를 넘겨주지 않았다. 그러므로 state 가 null 로 들어갔다.
         *
         * 오버로딩 되어있음
         * public final <T, E extends Throwable> T execute(RetryCallback<T, E> retryCallback, RecoveryCallback<T> recoveryCallback) throws E {
           return this.doExecute(retryCallback, recoveryCallback, (RetryState)null);
    }    *
         *
         * 그래서 다시 while 문 (반복문)으로 간다.
         * 현재 item 1(방금 에러가 발생한 아이템)을 다시 수행한다.
         * context 에 item 1의 count 는 2다. (재시도를 2번 했으므로)
         * 여기서 이제 더이상 재시도를 할 수 없으므로. 이후로 handleRetryExhausted()로 온다.
         * recover 콜백을 수행한다.
         */
        Customer result = retryTemplate.execute(new RetryCallback<Customer, RuntimeException>() {
                                                    @Override
                                                    public Customer doWithRetry(RetryContext context) throws RuntimeException {
                                                        // 설정된 조건 및 횟수만큼 재시도 수행
                                                        if (item.equals("1") || item.equals("2")) {
                                                            throw new RetryableException("failed");
                                                        }

                                                        return new Customer(item);
                                                    }
                                                }, new RecoveryCallback<Customer>() {
            /** 우리가 만든 RetryTemplate 을 탄다. */
            // springBatch 에서는 skip 이 있는지 체크하고 skip 이 있으면,
            // 반복문 쪽으로 가서 skip 된 데이터를 제거하고 다음 아이템으로 이동한다.
            // 우리는 skip 기능을 사용하지 않았다.
            // 그래서 retry 를 통해서 재시도를 통해서 계속적으로 오류가 나면 아래를 대안으로 장애 해결책을 둔 것이다.
            // 그래서 그 다음 item 으로 단계를 이어나가게된다.
                                                    @Override
                                                    public Customer recover(RetryContext context) throws Exception {
                                                        // 재시도가 모두 소진되었을 때 수행
                                                        return new Customer(item);
                                                    }
                                                },

                /** state 추가. 위 주석에서 이게 없을떄 null 이 되었었다. */
                // 현재 true 이므로 롤백이 수행된다.
                // recovery 에 예외가 발생하게 되면 처음으로 돌아가게 되어 에러가 발생한다.
                // 0번째 통과
                // 1번째 예외 발생
                // -> chunk 처음으로 이동 (state 를 설정해줘서 null 이 아니므로 springBatch 처럼 이동한다)
                // 처음으로 이동 - 다시금 0부터 시작하게된다.
                // 0번째 통과
                // 1번째 - 이미 count 가 1이다.
                // 여기서는 springBatch 가 제공해주는 RetryTemplate을 탄다.
                // 우리가 만약 retryLimit 을 설정해주지 않았다면, (스프링 배치에서는 retryLimit 을 1로 default 셋팅한다.)
                // 그래서 count 가 1인데 recoveryCallback 로직으로 왔다.
                /** 왜 우리는 2로 설정해줬는데?
                 * 현재 수행되는 RetryTemplate 이 우리가 만든게 아니라 springBatch 의 RetryTemplate 이다.
                 * 우리가 retryLimit 을 따로 안줬으므로 count 가 1번만 수행되고 재시도를 하지 않기 때문이다.
                 */
                // skip 설정 여부를 판단하고, 안해줬으므로 그 다음, rollback 여부는 true 이므로 롤백을 수행하고 에러를 발생시킨다.
                // 그래서 step 은 실패한다. 더이상 재시도도 할 수 없다.

                // 우리가 skip 설정을 주면 에러 발생한 item1, item2 는 skip 된다.

                /** 중요 */
                // springBatch 에서 수행되고있는 RetryTemplate 이 현재 우리가 구현한 RetryTemplate 와 연계되어 수행된다.
                // DefaultRetryState 가 없으면 RetryTemplate 의 객체도 생성이 안되서 context 도 제대로 생성이 안된다.
                // 그래서 springBatch 가 DefaultRetryState 가 없을땐 처음으로 돌아가서 재시도하는 로직이 아니라,
                // 그 item 에 해당하는 재시도의 횟수만큼만 수행하도록만 한다.
                new DefaultRetryState(item, rollbackClassifier));
        //template - state 추가, skip 추가, backoff 추가,
        return result;
    }
}
