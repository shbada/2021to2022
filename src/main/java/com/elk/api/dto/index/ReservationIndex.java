package com.elk.api.dto.index;

import lombok.Builder;
import lombok.Data;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Data
@Document(indexName = "reservation")
@Setting(settingPath = "elastic-setting.json")
public class ReservationIndex {
    /* id */
    @Id
    @Field(type = FieldType.Long)
    private int reservationId;

    /* 서비스구분 */
    @Field(type = FieldType.Keyword)
    private String type;

    /* 서비스ID */
    @Field(type = FieldType.Keyword)
    private String serviceId;

    /* 장소 X 좌표, Y 좌 */
    @GeoPointField
    private GeoPoint location; // X, Y

    /* 서비스개시시작일시 */
    @Field(type = FieldType.Date)
    private String startDate;

    /* 서비스개시종료일시 */
    @Field(type = FieldType.Date)
    private String endDate;

    /* 이미지경로 */
    @Field(type = FieldType.Text)
    private String imgPath;

    /* 대분류명 *//*
    @Field(type = FieldType.Keyword)
    private String MAXCLASSNM;

    *//* 소분류명 *//*
    @Field(type = FieldType.Keyword)
    private String MINCLASSNM;

    *//* 서비스상태 *//*
    @Field(type = FieldType.Keyword)
    private String SVCSTATNM;

    *//* 서비스명 *//*
    @Field(type = FieldType.Keyword)
    private String SVCNM;

    *//* 결제방법 *//*
    @Field(type = FieldType.Keyword)
    private String PAYATNM;

    *//* 장소명 *//*
    @Field(type = FieldType.Keyword)
    private String PLACENM;

    *//* 서비스대상 *//*
    @Field(type = FieldType.Keyword)
    private String USETGTINFO;

    *//* 바로가기URL *//*
    @Field(type = FieldType.Keyword)
    private String SVCURL;

    *//* 장소 X좌표 *//*
    // private String X;

    *//* 장소 Y좌표 *//*
    // private String Y;

    *//* 접수시작일시 *//*
    @Field(type = FieldType.Date)
    private String RCPTBGNDT;

    *//* 접수종료일시 *//*
    @Field(type = FieldType.Date)
    private String RCPTENDDT;

    *//* 지역명 *//*
    @Field(type = FieldType.Text)
    private String AREANM;

    *//* 주의사항 *//*
    @Field(type = FieldType.Text)
    private String NOTICE;

    *//* 상세내용 *//*
    @Field(type = FieldType.Text)
    private String DTLCONT;

    *//* 전화번호 *//*
    @Field(type = FieldType.Text)
    private String TELNO;

    *//* 서비스이용 종료시간 *//*
    @Field(type = FieldType.Date)
    private String V_MAX;

    *//* 서비스이용 시작시간 *//*
    @Field(type = FieldType.Date)
    private String V_MIN;

    *//* 취소기간 기준정보 *//*
    @Field(type = FieldType.Text)
    private String REVSTDDAYNM;

    *//* 취소기간 기준일까지 *//*
    @Field(type = FieldType.Date)
    private String REVSTDDAY;*/
}

