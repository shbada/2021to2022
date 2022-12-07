package com.itvillage.section03;

import org.junit.Test;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * blockingForEach를 사용해 통지된 데이터 전부를 테스트 한다.
 *
 * - blockingForEach 내부에서 각각의 데이터를 얻어서 처리할 수 있다.
 * - 생산자가 통지한 데이터를 순차적으로 모두 통지한다.
 * - 통지된 각각의 데이터가 모두 조건에 맞아야 true 를 반환한다.
 * (blocklingForEach() 는 결과 return이 없는데, 여기서 true 반환한다는 의미는,
 * 함수 내부에서 assertThat을 사용해서 단위 테스트를 진행할때 검증되는 모든 데이터가 패스되어야 결론적으로
 * 테스트가 true가 된다는 말이다.)
 */
public class _6_blockingForEachTest {
    // A 구간의 속도 중에서 110 이상인 속도만 필터링이 되었는지 테스트
    @Test
    public void getSpeedOfSectionAForEachTest() {
        SampleObservable.getSpeedOfSectionA()
                .filter(speed -> speed > 110)
                // 리턴값이 없는 Consumer 함수형 인터페이스 구현
                .blockingForEach(speed -> assertThat(speed, greaterThan(110)));
    }
}
