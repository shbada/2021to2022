package com.mileage.application;

import com.mileage.domain.mileage.MileageCommand;
import com.mileage.domain.mileage.MileageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
}
