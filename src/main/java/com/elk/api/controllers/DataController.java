package com.elk.api.controllers;

import com.elk.api.common.CommonResponse;
import com.elk.api.dto.OpenApiListDto;
import com.elk.api.service.ElkService;
import com.elk.api.dto.index.ReservationIndex;
import com.elk.api.service.DataService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = {"DataController"})
@RestController
@RequestMapping("/data")
@RequiredArgsConstructor
@Slf4j
public class DataController {
    private final CommonResponse commonResponse;
    private final DataService dataService;
    private final ElkService elkService;

    /**
     * getData
     * @return
     */
    @GetMapping("/getData")
    public ResponseEntity<?> getOpenApiData() throws Exception {
        /* open api call */
        List<OpenApiListDto> openApiListDtoList = dataService.getOpenApiData();

        /* dto to index */
        List<ReservationIndex> paramReservationIndexList = dataService.toIndex(openApiListDtoList);

        /* elk insert documnet */
        List<ReservationIndex> reservationIndexList = elkService.insertDocument(paramReservationIndexList);
        return commonResponse.send(reservationIndexList);
    }
}
