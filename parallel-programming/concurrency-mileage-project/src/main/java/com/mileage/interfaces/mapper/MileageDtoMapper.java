package com.mileage.interfaces.mapper;

import com.mileage.common.util.DateUtil;
import com.mileage.domain.enums.MileageManageType;
import com.mileage.domain.mileage.MileageCommand;
import com.mileage.interfaces.dto.MileageDto;
import com.mileage.interfaces.dto.MileageHistoryDto;

import java.util.List;
import java.util.stream.Collectors;

public class MileageDtoMapper {
    public static MileageCommand.SavePoint of(MileageDto.ChargeRequest request) {
        return new MileageCommand.SavePoint(request.getMileageIdx(), request.getPoint(), MileageManageType.CHARGE);
    }

    public static MileageCommand.SavePoint of(MileageDto.UseRequest request) {
        return new MileageCommand.SavePoint(request.getMileageIdx(), request.getPoint(), MileageManageType.USE);
    }

    public static List<MileageDto.InfoResponse> of(List<MileageCommand.Info> infoList) {
        return infoList.stream()
                .map(info -> {
                    MileageDto.InfoResponse infoResponse = new MileageDto.InfoResponse();
                    infoResponse.setMileageIdx(info.getMileageIdx());
                    infoResponse.setPoint(info.getPoint());

                    return infoResponse;
                })
                .collect(Collectors.toList());
    }
}
