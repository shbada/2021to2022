package com.spring.batch.part9;

import com.spring.batch.part4.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryListener;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.retry.support.RetryTemplateBuilder;

@Slf4j
public class ItemValidationRetryProcessor implements ItemProcessor<Person, Person> {
    private final RetryTemplate retryTemplate;

    public ItemValidationRetryProcessor() {
        this.retryTemplate = new RetryTemplateBuilder()
                /**
                 * 정상데이터 3건 + name 빈 데이터 3건 일 경우
                 * 빈데이터 1, 2, 3 중에
                 * 1번의 경우-> 에러발생시 계속해서 재시도 (3번까지) 그 후 RecoveryCallback 호출.
                 */
                .maxAttempts(3)
                .retryOn(NotFoundNameException.class) // 3번 발생할때까지 허용하고 재시도.
                /* 리스너 적용 */
                .withListener(new ItemRetryListener())
                .build();
    }
    @Override
    public Person process(Person person) throws Exception {
        return this.retryTemplate.execute(context -> {
            // RetryCallback
            if (person.isNotEmptyName()) {
                return person;
            }

            throw new NotFoundNameException(); /* NotFoundNameException 에러가 3번까지 호출이 되면 */
        }, context -> {
            // RecoveryCallback
            log.info("retryCallback call : " + person.getId());
            return person.unknownName(); /* 위 NotFoundNameException 에러가 3번까지 호출이 되면, 얘가 호출됨 */
        });
    }

    /**
     * RetryListener
     * 위 .withListener(new ItemRetryListener()) 추가
     */
    public static class ItemRetryListener implements RetryListener {

        @Override
        public <T, E extends Throwable> boolean open(RetryContext retryContext, RetryCallback<T, E> retryCallback) {
            return true; /* true 여야 retry 가 적용됨 */
        }

        /**
         * retry 종료 후에 호출
         * @param retryContext
         * @param retryCallback
         * @param throwable
         * @param <T>
         * @param <E>
         */
        @Override
        public <T, E extends Throwable> void close(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
            log.info("close");
        }

        /**
         * retryTemplate 안에 정의한 NotFoundNameException 이 발생했을때 호출
         * @param retryContext
         * @param retryCallback
         * @param throwable
         * @param <T>
         * @param <E>
         */
        @Override
        public <T, E extends Throwable> void onError(RetryContext retryContext, RetryCallback<T, E> retryCallback, Throwable throwable) {
            /* 반복해서 호출되겠지 (NotFoundNameException 가 재시도되면서 3번 호출되기 때문) */
            log.info("onError");
        }
    }
}
