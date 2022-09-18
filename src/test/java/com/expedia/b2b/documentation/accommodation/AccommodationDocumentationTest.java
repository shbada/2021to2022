package com.expedia.b2b.documentation.accommodation;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.restdocs.restassured3.RestDocumentationFilter;

import static com.expedia.b2b.documentation.DocumentationUtils.getDocumentRequest;
import static com.expedia.b2b.documentation.DocumentationUtils.getDocumentResponse;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.restassured3.RestAssuredRestDocumentation.document;

public class AccommodationDocumentationTest {
    public static RestDocumentationFilter 숙박업체_생성_요청() {
        return document("post-accommodations",
                getDocumentRequest(),
                getDocumentResponse(),
                requestHeaders(
                        headerWithName(HttpHeaders.CONTENT_TYPE).description("Content-type")
                ),
                requestFields(
                        /* 숙박업체_정보_셋팅 */
                        fieldWithPath("accommodationName").description("숙박업체명"),
                        fieldWithPath("accommodationType").description("숙박업체 타입"),
                        fieldWithPath("bizno").description("사업자번호"),
                        fieldWithPath("countryCd").description("국가코드"),
                        fieldWithPath("city").description("시/도"),
                        fieldWithPath("state").description("행정구역"),
                        fieldWithPath("street").description("나머지주소"),
                        fieldWithPath("zipcode").description("우편번호"),
                        fieldWithPath("checkinDescription").description("체크인 상세설명"),
                        fieldWithPath("checkinStartTime").description("체크인 시작시간"),
                        fieldWithPath("checkoutEndTime").description("체크인 종료시간"),
                        fieldWithPath("minCheckinAge").description("체크인 가능 최소 나이"),
                        fieldWithPath("respeUserId").description("담당자 유저 인덱스"),
                        fieldWithPath("respeEmail").description("담당자 이메일"),
                        fieldWithPath("respeHpno").description("담당자 휴대폰번호"),
                        fieldWithPath("telNo").description("숙박업체 전화번호"),
                        fieldWithPath("lastModifiedBy").description("수정자"),
                        fieldWithPath("createdBy").description("등록자"),

                        /* 숙박업체_권한_정보_셋팅 */
                        fieldWithPath("progStatusCd").description("진행상태 코드"),
                        fieldWithPath("stoppedDts").description("중지일자"),
                        fieldWithPath("stoppedRsn").description("중지사유"),
                        fieldWithPath("lastModifiedBy").description("수정자"),
                        fieldWithPath("createdBy").description("등록자"),

                        /* 객실_정보_셋팅 */
                        fieldWithPath("roomType").description("객실 타입"),
                        fieldWithPath("roomName").description("객실명"),
                        fieldWithPath("maxEntranceCnt").description("투숙 가능 인원"),
                        fieldWithPath("lastModifiedBy").description("수정자"),
                        fieldWithPath("createdBy").description("등록자"),

                        /* 객실_부가정보_셋팅 */
                        fieldWithPath("codeId").description("숙박업체 공통코드 인덱스"),
                        fieldWithPath("active").description("공통코드 제공여부"),
                        fieldWithPath("lastModifiedBy").description("수정자"),
                        fieldWithPath("createdBy").description("등록자"),

                        /* 객실_요금_셋팅 */
                        fieldWithPath("active").description("기본요금 설정 여부"),
                        fieldWithPath("feeName").description("객실 요금제명"),
                        fieldWithPath("fee").description("객실 요금"),
                        fieldWithPath("lastModifiedBy").description("수정자"),
                        fieldWithPath("createdBy").description("등록자")
                ),
                responseFields(
                        fieldWithPath("accommodationId").description("생성한 숙박업체 key")
                )
        );
    }
}
