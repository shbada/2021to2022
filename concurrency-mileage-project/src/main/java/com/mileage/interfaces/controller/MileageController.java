package com.mileage.interfaces.controller;

import com.mileage.application.MileageFacade;
import com.mileage.common.response.CommonResponse;
import com.mileage.common.response.ResponseDto;
import com.mileage.domain.mileage.MileageCommand;
import com.mileage.domain.mileagehistory.MileageHistoryCommand;
import com.mileage.domain.mileagehistory.MileageHistorySearchCommand;
import com.mileage.interfaces.dto.MileageDto;
import com.mileage.interfaces.dto.MileageHistoryDto;
import com.mileage.interfaces.mapper.MileageDtoMapper;
import com.mileage.interfaces.mapper.MileageHistoryDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mileage")
public class MileageController {
    private final MileageFacade mileageFacade;

    /**
     * 포인트 충전
     * @param request
     * @return
     */
    @PostMapping("/charge")
    public ResponseDto<Integer> chargePoint(@RequestBody MileageDto.ChargeRequest request) {
        /* dto to command */
        MileageCommand.SavePoint chargePoint = MileageDtoMapper.of(request);

        /* save */
        int totalPoint = mileageFacade.chargePoint(chargePoint);

        return CommonResponse.success(totalPoint);
    }

    /**
     * 포인트 사용
     * @param request
     * @return
     */
    @PostMapping("/use")
    public ResponseDto<Integer> usePoint(@RequestBody MileageDto.UseRequest request) {
        /* dto to command */
        MileageCommand.SavePoint usePoint = MileageDtoMapper.of(request);

        /* save */
        int totalPoint = mileageFacade.usePoint(usePoint);

        return CommonResponse.success(totalPoint);
    }

    /**
     * 포인트 리스트 조회
     * @return
     */
    @GetMapping
    public ResponseDto<List<MileageDto.InfoResponse>> getPointList() {
        /* get */
        List<MileageCommand.Info> pointList = mileageFacade.getPointList();

        /* command to dto */
        List<MileageDto.InfoResponse> infoResponseList = MileageDtoMapper.of(pointList);

        return CommonResponse.success(infoResponseList);
    }

    /**
     * 포인트 리스트 조회
     * @return
     */
    @GetMapping("async")
    public ResponseDto<List<MileageDto.InfoResponse>> getAsyncPointList() {
        /* get */
        List<MileageCommand.Info> pointList = mileageFacade.getAsyncPointList();

        /* command to dto */
        List<MileageDto.InfoResponse> infoResponseList = MileageDtoMapper.of(pointList);

        return CommonResponse.success(infoResponseList);
    }
}
