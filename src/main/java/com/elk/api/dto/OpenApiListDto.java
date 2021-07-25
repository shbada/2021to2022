package com.elk.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
        "GUBUN",
        "SVCID",
        "MAXCLASSNM",
        "MINCLASSNM",
        "SVCSTATNM",
        "SVCNM",
        "PAYATNM",
        "PLACENM",
        "USETGTINFO",
        "SVCURL",
        "X",
        "Y",
        "SVCOPNBGNDT",
        "SVCOPNENDDT",
        "RCPTBGNDT",
        "RCPTENDDT",
        "AREANM",
        "NOTICE",
        "IMG_PATH",
        "DTLCONT",
        "TELNO",
        "V_MAX",
        "V_MIN",
        "REVSTDDAYNM",
        "REVSTDDAY"
})
public class OpenApiListDto {
    /* 서비스구분 */
    @JsonProperty("GUBUN")
    private String GUBUN;

    /* 서비스ID */
    @JsonProperty("SVCID")
    private String SVCID;

    /* 대분류명 */
    @JsonProperty("MAXCLASSNM")
    private String MAXCLASSNM;

    /* 소분류명 */
    @JsonProperty("MINCLASSNM")
    private String MINCLASSNM;

    /* 서비스상태 */
    @JsonProperty("SVCSTATNM")
    private String SVCSTATNM;

    /* 서비스명 */
    @JsonProperty("SVCNM")
    private String SVCNM;

    /* 결제방법 */
    @JsonProperty("PAYATNM")
    private String PAYATNM;

    /* 장소명 */
    @JsonProperty("PLACENM")
    private String PLACENM;

    /* 서비스대상 */
    @JsonProperty("USETGTINFO")
    private String USETGTINFO;

    /* 바로가기URL */
    @JsonProperty("SVCURL")
    private String SVCURL;

    /* 장소X좌표 */
    @JsonProperty("X")
    private String X;

    /* 장소Y좌표 */
    @JsonProperty("Y")
    private String Y;

    /* 서비스개시시작일시 */
    @JsonProperty("SVCOPNBGNDT")
    private String SVCOPNBGNDT;

    /* 서비스개시종료일시 */
    @JsonProperty("SVCOPNENDDT")
    private String SVCOPNENDDT;

    /* 접수시작일시 */
    @JsonProperty("RCPTBGNDT")
    private String RCPTBGNDT;

    /* 접수종료일시 */
    @JsonProperty("RCPTENDDT")
    private String RCPTENDDT;

    /* 지역명 */
    @JsonProperty("AREANM")
    private String AREANM;

    /* 주의사항 */
    @JsonProperty("NOTICE")
    private String NOTICE;

    /* 이미지경로 */
    @JsonProperty("IMG_PATH")
    private String IMG_PATH;

    /* 상세내용 */
    @JsonProperty("DTLCONT")
    private String DTLCONT;

    /* 전화번호 */
    @JsonProperty("TELNO")
    private String TELNO;

    /* 서비스이용 종료시간 */
    @JsonProperty("V_MAX")
    private String V_MAX;

    /* 서비스이용 시작시간 */
    @JsonProperty("V_MIN")
    private String V_MIN;

    /* 취소기간 기준정보 */
    @JsonProperty("REVSTDDAYNM")
    private String REVSTDDAYNM;

    /* 취소기간 기준일까지 */
    @JsonProperty("REVSTDDAY")
    private String REVSTDDAY;
}
