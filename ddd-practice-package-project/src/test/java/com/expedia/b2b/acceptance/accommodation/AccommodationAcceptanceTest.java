package com.expedia.b2b.acceptance.accommodation;

import com.expedia.b2b.acceptance.AcceptanceTest;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static com.expedia.b2b.acceptance.accommodation.AccommodationSteps.숙박업체_생성_요청;
import static com.expedia.b2b.acceptance.accommodation.AccommodationSteps.숙박업체_조회_요청;
import static org.assertj.core.api.Assertions.assertThat;

public class AccommodationAcceptanceTest extends AcceptanceTest {
    /*
       - given : 숙박업체 정보를 생성
       - when  : 숙박업체 정보를 기반으로 생성 요청
       - then  : 원하는 숙박업체의 정보로 숙박업체가 신규 생성됨
     */
    @Test
    void 숙박업체_생성() {
        // given
        // when
        ExtractableResponse<Response> response = 숙박업체_생성_요청();

        // then
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    /*
       - given : 숙박업체 정보를 생성
       - when  : 생성된 숙박업체 정보 조회 요청
       - then  : 정상적으로 생성된 숙박업체가 조회됨
     */
    @Test
    void 숙박업체_조회() {
        // given
        ExtractableResponse<Response> response = 숙박업체_생성_요청();
        Long accommodationId = response.jsonPath().getLong("accommodationId");

        // when
        ExtractableResponse<Response> accommodationResponse = 숙박업체_조회_요청(accommodationId);

        // then
        assertThat(accommodationResponse.statusCode()).isEqualTo(HttpStatus.OK.value());
        assertThat(accommodationResponse.jsonPath().getLong("accommodationId")).isEqualTo(accommodationId);
    }
}
