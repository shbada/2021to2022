package com.expedia.b2b.acceptance.accommodation;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Map;

import static com.expedia.b2b.acceptance.accommodation.AccommodationParams.객실_부가정보_셋팅;
import static com.expedia.b2b.acceptance.accommodation.AccommodationParams.객실_요금_셋팅;
import static com.expedia.b2b.acceptance.accommodation.AccommodationParams.객실_정보_셋팅;
import static com.expedia.b2b.acceptance.accommodation.AccommodationParams.숙박업체_권한_정보_셋팅;
import static com.expedia.b2b.acceptance.accommodation.AccommodationParams.숙박업체_정보_셋팅;

public class AccommodationSteps {
    @Test
    static ExtractableResponse<Response> 숙박업체_생성_요청() {
        Map<String, Object> accommodationMap = 숙박업체_정보_셋팅();
        Map<String, Object> accommodationRoleMap = 숙박업체_권한_정보_셋팅();
        Map<String, Object> accommodationRoomMap = 객실_정보_셋팅();
        Map<String, Object> accommodationRoomInfoMap = 객실_부가정보_셋팅();
        Map<String, Object> accommodationRoomFeeMap = 객실_요금_셋팅();

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("accommodationDto", accommodationMap);
        paramMap.put("accommodationRoleDto", accommodationRoleMap);
        paramMap.put("accommodationRoomDto", accommodationRoomMap);
        paramMap.put("accommodationRoomInfoDto", accommodationRoomInfoMap);
        paramMap.put("accommodationRoomFeeDto", accommodationRoomFeeMap);

        return RestAssured
                .given()
                    .log().all()
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .body(paramMap)
                .when()
                    .post("/accommodations")
                .then()
                    .log().all()
                .extract();
    }

    @Test
    static ExtractableResponse<Response> 숙박업체_조회_요청(Long accommodationId) {
        return RestAssured
                .given()
                    .log().all()
                .when()
                    .get("/accommodation/{accommodationId}", accommodationId)
                .then()
                    .log().all()
                .extract();
    }
}
