package com.elk.api.service;

import com.elk.api.common.enums.EnumOpenApiResponse;
import com.elk.api.common.exceptions.BadRequestException;
import com.elk.api.dto.OpenApiBaseDto;
import com.elk.api.dto.OpenApiDto;
import com.elk.api.dto.OpenApiListDto;
import com.elk.api.dto.index.ReservationIndex;
import com.elk.api.feign.OpenApiClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataService {
    private final OpenApiClient openApiClient;

    /**
     * open Api Call (get Data)
     * @return
     */
    public List<OpenApiListDto> getOpenApiData() throws Exception {
        /* return List */
        List<OpenApiListDto> resultList;

        /* open Api Call */
        String jsonString = openApiClient.getOpenApiData(0, 999);
        log.info("result : " + jsonString);

        /* get Data, set Data */
        ObjectMapper objectMapper = new ObjectMapper();

        OpenApiBaseDto openApiBaseDto = objectMapper.readValue(jsonString, new TypeReference<OpenApiBaseDto>(){});
        OpenApiDto openApiDto = openApiBaseDto.getListPublicReservationEducation();

        /* 성공! */
        if (EnumOpenApiResponse.SUCCESS.getCode().equals(openApiDto.getResult().getCode())) {
            resultList = new ArrayList<>(openApiDto.getRow());
        } else {
            throw new BadRequestException(openApiDto.getResult().getMessage());
        }

        log.info("resultList total Count : " + resultList.size());
        return resultList;
    }

    public List<ReservationIndex> toIndex(List<OpenApiListDto> openApiListDtoList) {
        List<ReservationIndex> reservationIndexList = new ArrayList<>();

        int index = 0;

        /* TODO builder 패턴 적용 */
        for (OpenApiListDto openApiListDto : openApiListDtoList) {
            ReservationIndex reservationIndex = new ReservationIndex();

            reservationIndex.setReservationId(index++);
            reservationIndex.setType(openApiListDto.getGUBUN());
            reservationIndex.setServiceId(openApiListDto.getSVCID());

            if (!StringUtils.hasText(openApiListDto.getX())) {
                openApiListDto.setX("0");
            }

            if (!StringUtils.hasText(openApiListDto.getY())) {
                openApiListDto.setY("0");
            }

            GeoPoint geoPoint = new GeoPoint(Double.parseDouble(openApiListDto.getX()), Double.parseDouble(openApiListDto.getY()));
            reservationIndex.setLocation(geoPoint);

            reservationIndex.setStartDate(openApiListDto.getSVCOPNBGNDT());
            reservationIndex.setEndDate(openApiListDto.getSVCOPNBGNDT());
            reservationIndex.setImgPath(openApiListDto.getIMG_PATH());

            /*reservationIndex.setMAXCLASSNM(openApiListDto.getMAXCLASSNM());
            reservationIndex.setMINCLASSNM(openApiListDto.getMINCLASSNM());
            reservationIndex.setSVCSTATNM(openApiListDto.getSVCSTATNM());
            reservationIndex.setSVCNM(openApiListDto.getSVCNM());
            reservationIndex.setPAYATNM(openApiListDto.getPAYATNM());
            reservationIndex.setPLACENM(openApiListDto.getPLACENM());
            reservationIndex.setUSETGTINFO(openApiListDto.getUSETGTINFO());
            reservationIndex.setSVCURL(openApiListDto.getSVCURL());
            reservationIndex.setRCPTBGNDT(openApiListDto.getRCPTBGNDT());
            reservationIndex.setRCPTENDDT(openApiListDto.getRCPTENDDT());
            reservationIndex.setAREANM(openApiListDto.getAREANM());
            reservationIndex.setNOTICE(openApiListDto.getNOTICE());
            reservationIndex.setDTLCONT(openApiListDto.getDTLCONT());
            reservationIndex.setTELNO(openApiListDto.getTELNO());
            reservationIndex.setV_MAX(openApiListDto.getV_MAX());
            reservationIndex.setV_MIN(openApiListDto.getV_MIN());
            reservationIndex.setREVSTDDAYNM(openApiListDto.getREVSTDDAYNM());
            reservationIndex.setREVSTDDAY(openApiListDto.getREVSTDDAY());*/

            reservationIndexList.add(reservationIndex);
        }

        return reservationIndexList;
    }
}
