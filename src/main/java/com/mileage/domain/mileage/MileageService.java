package com.mileage.domain.mileage;

import com.mileage.common.exception.BadRequestException;
import com.mileage.common.lock.RedissonExecuteService;
import com.mileage.common.lock.RedissonLock;
import com.mileage.common.response.ErrorCode;
import com.mileage.domain.Mileage;
import com.mileage.domain.mileagehistory.MileageHistoryService;
import com.mileage.infrastructure.MileageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MileageService {
    private final MileageStore mileageStore;
    private final MileageReader mileageReader;
    private final MileageHistoryService mileageHistoryService;
    private final RedissonExecuteService redissonExecuteService;
    private final MileageRepository mileageRepository;

    @RedissonLock(key = "mileageIdx")
    public int charge(Long mileageIdx, MileageCommand.SavePoint savePoint) {
        /* get point */
        Mileage getMileage = getMileage(mileageIdx);

        log.info("before : " + getMileage.getPoint());

        /* charge */
        getMileage.charge(savePoint.getPoint());

        log.info("after : " + getMileage.getPoint());

        /* save history */
        mileageHistoryService.savePointHistory(savePoint, getMileage);

        return getMileage.getPoint();
    }

    /**
     * 람다를 사용한 방식
     * @param mileageIdx
     * @param savePoint
     * @return
     */
    public int chargeLambda(Long mileageIdx, MileageCommand.SavePoint savePoint) {
        Mileage mileage = redissonExecuteService.execute(savePoint.getMileageIdx(), () -> {
            /* get point */
            Mileage getMileage = getMileage(mileageIdx);

            log.info("before : " + getMileage.getPoint());

            /* charge */
            getMileage.charge(savePoint.getPoint());

            log.info("after : " + getMileage.getPoint());

            /* commit */
            mileageRepository.saveAndFlush(getMileage);

            /* save history */
            mileageHistoryService.savePointHistory(savePoint, getMileage);

            return getMileage;
        });

        return mileage.getPoint();
    }

    @RedissonLock(key = "mileageIdx")
    public int use(Long mileageIdx, MileageCommand.SavePoint savePoint) {
        /* get point */
        Mileage getMileage = getMileage(mileageIdx);

        log.info("before : " + getMileage.getPoint());

        /* use */
        getMileage.use(savePoint.getPoint()); // 동시성 제어 필요

        log.info("after : " + getMileage.getPoint());

        return getMileage.getPoint();
    }

    private Mileage getMileage(Long mileageIdx) {
        Optional<Mileage> getMileage = mileageReader.findById(mileageIdx);

        if (getMileage.isEmpty()) {
            throw new BadRequestException(ErrorCode.NOT_EXIST_POINT);
        }

        return getMileage.get();
    }
}
