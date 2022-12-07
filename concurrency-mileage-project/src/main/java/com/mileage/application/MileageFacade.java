package com.mileage.application;

import com.mileage.domain.Mileage;
import com.mileage.domain.MileageHistory;
import com.mileage.domain.mileage.MileageCommand;
import com.mileage.domain.mileage.MileageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MileageFacade {
    private final MileageService mileageService;

    public int chargePoint(MileageCommand.SavePoint savePoint) {
        /* charge */
        return mileageService.charge(savePoint.getMileageIdx(), savePoint);
    }

    public int usePoint(MileageCommand.SavePoint savePoint) {
        /* use */
        return mileageService.use(savePoint.getMileageIdx(), savePoint);
    }

    /**
     * 동기
     * @return
     */
    public List<MileageCommand.Info> getPointList() {
        List<Mileage> pointList = mileageService.getPointList();

        return pointList.stream()
                .map(Mileage::toCommand)
                .collect(Collectors.toList());
    }

    /**
     * 비동기
     * @return
     */
    public List<MileageCommand.Info> getAsyncPointList() {
        List<Mileage> pointList = mileageService.getAsyncPointList();

        return pointList.stream()
                .map(Mileage::toCommand)
                .collect(Collectors.toList());
    }
}
