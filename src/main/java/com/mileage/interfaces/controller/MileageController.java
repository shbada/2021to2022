package com.mileage.interfaces.controller;

import com.mileage.application.MileageFacade;
import com.mileage.common.response.CommonResponse;
import com.mileage.common.response.ResponseDto;
import com.mileage.domain.mileage.MileageCommand;
import com.mileage.interfaces.dto.MileageDto;
import com.mileage.interfaces.mapper.MileageDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
