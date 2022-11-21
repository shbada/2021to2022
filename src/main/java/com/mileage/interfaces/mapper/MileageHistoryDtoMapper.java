package com.mileage.interfaces.mapper;

import com.mileage.common.util.DateUtil;
import com.mileage.domain.mileageHistory.MileageHistoryCommand;
import com.mileage.domain.mileageHistory.MileageHistorySearchCommand;
import com.mileage.interfaces.dto.MileageHistoryDto;

import java.util.List;
import java.util.stream.Collectors;

public class MileageHistoryDtoMapper {
    public static MileageHistorySearchCommand.SearchPointHistory of(MileageHistoryDto.searchRequest request) {
        return new MileageHistorySearchCommand.SearchPointHistory(
                request.getMileageIdx(), request.getStartDate(), request.getEndDate());
    }

    public static List<MileageHistoryDto.InfoResponse> of(List<MileageHistoryCommand.Info> infoList) {
        return infoList.stream()
                .map(getPointHistory -> {
                    MileageHistoryDto.InfoResponse infoResponse = new MileageHistoryDto.InfoResponse();
                    infoResponse.setMileageIdx(getPointHistory.getMileageIdx());
                    infoResponse.setPoint(getPointHistory.getPoint());
                    infoResponse.setMileageManageType(getPointHistory.getMileageManageType());
                    infoResponse.setCreatedAt(DateUtil.dateTimeToString(getPointHistory.getCreatedAt()));

                    return infoResponse;
                })
                .collect(Collectors.toList());
    }
}
