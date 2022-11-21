package com.mileage.interfaces.mapper;

import com.mileage.domain.enums.MileageManageType;
import com.mileage.domain.mileage.MileageCommand;
import com.mileage.interfaces.dto.MileageDto;

public class MileageDtoMapper {
    public static MileageCommand.SavePoint of(MileageDto.ChargeRequest request) {
        return new MileageCommand.SavePoint(request.getMileageIdx(), request.getPoint(), MileageManageType.CHARGE);
    }

    public static MileageCommand.SavePoint of(MileageDto.UseRequest request) {
        return new MileageCommand.SavePoint(request.getMileageIdx(), request.getPoint(), MileageManageType.USE);
    }
}
