package com.mileage.interfaces.controller;

import com.mileage.application.MileageHistoryFacade;
import com.mileage.common.response.CommonResponse;
import com.mileage.common.response.ResponseDto;
import com.mileage.domain.mileageHistory.MileageHistoryCommand;
import com.mileage.domain.mileageHistory.MileageHistorySearchCommand;
import com.mileage.interfaces.dto.MileageHistoryDto;
import com.mileage.interfaces.mapper.MileageHistoryDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mileage-history")
public class MileageHistoryController {
    private final MileageHistoryFacade mileageHistoryFacade;

    /**
     * 포인트 내역 검색
     * @param request
     * @return
     */
    @GetMapping
    public ResponseDto<List<MileageHistoryDto.InfoResponse>> getPointHistoryList(@ModelAttribute MileageHistoryDto.searchRequest request) {
        /* dto to command */
        MileageHistorySearchCommand.SearchPointHistory pointHistorySearchCommand = MileageHistoryDtoMapper.of(request);

        /* search */
        List<MileageHistoryCommand.Info> pointHistoryList = mileageHistoryFacade.getPointHistoryList(pointHistorySearchCommand);

        /* command to dto */
        List<MileageHistoryDto.InfoResponse> infoResponseList = MileageHistoryDtoMapper.of(pointHistoryList);

        return CommonResponse.success(infoResponseList);
    }
}
