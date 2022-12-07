package com.mileage.domain.mileage;

import com.mileage.domain.Mileage;
import com.mileage.domain.enums.MileageManageType;
import com.mileage.infrastructure.MileageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MileageServiceTest {
    @Autowired
    private MileageService mileageService;

    @Autowired
    private MileageRepository mileageRepository;

    final int threadCount = 30;

    @Test
    void 포인트_충전을_동시에_여러건_처리한다() throws InterruptedException {
        // given
        int chargePoint = 10000;

        /* 포인트 등록 */
        Long mileageIdx = createMileage(0);

        // when
        this.chargePointExecute(mileageIdx, chargePoint);

        // then
        List<Mileage> all = mileageRepository.findAll();
        int totalPoint = all.stream()
                .filter(getPoint -> mileageIdx.equals(getPoint.getIdx()))
                .mapToInt(Mileage::getPoint)
                .sum();

        assertThat(totalPoint).isEqualTo(chargePoint * threadCount);
    }

    @Test
    void 포인트_사용을_동시에_여러건_처리한다() throws InterruptedException {
        // given
        int chargePoint = 100000;
        int usePoint = 2000;

        /* 포인트 등록 */
        Long mileageIdx = createMileage(chargePoint);

        // when
        this.usePointExecute(mileageIdx, chargePoint, usePoint);

        // then
        List<Mileage> all = mileageRepository.findAll();
        int totalPoint = all.stream()
                .filter(getPoint -> mileageIdx.equals(getPoint.getIdx()))
                .mapToInt(Mileage::getPoint)
                .sum();

        assertThat(totalPoint).isEqualTo(chargePoint - (usePoint * threadCount));
    }

    /**
     * 포인트 적립 given when
     */
    private void chargePointExecute(Long mileageIdx, int chargePoint) throws InterruptedException {
        // given
        MileageCommand.SavePoint savePoint = new MileageCommand.SavePoint();
        savePoint.setPoint(chargePoint);
        savePoint.setMileageManageType(MileageManageType.CHARGE);

        // when
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        IntStream.range(0, threadCount).forEach(e -> executorService.submit(() -> {
                    try {
                        mileageService.charge(mileageIdx, savePoint);
                    } finally {
                        countDownLatch.countDown();
                    }
                }
        ));

        countDownLatch.await();
    }

    /**
     * 포인트 사용 given when
     */
    private void usePointExecute(Long mileageIdx, int chargePoint, int usePoint) throws InterruptedException {
        // given
        createMileage(chargePoint);

        MileageCommand.SavePoint savePoint = new MileageCommand.SavePoint();
        savePoint.setPoint(usePoint);
        savePoint.setMileageManageType(MileageManageType.USE);

        // when
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        IntStream.range(0, threadCount).forEach(e -> executorService.submit(() -> {
                    try {
                        mileageService.use(mileageIdx, savePoint);
                    } finally {
                        countDownLatch.countDown();
                    }
                }
        ));

        countDownLatch.await();
    }

    /**
     * Mileage 생성
     */
    private Long createMileage(int chargePoint) {
        Mileage newMileage = new Mileage();
        newMileage.charge(chargePoint);

        return mileageRepository.save(newMileage).getIdx();
    }
}