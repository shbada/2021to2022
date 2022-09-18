package com.expedia.b2b.documentation.accommodation;

import com.expedia.b2b.documentation.Documentation;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Test;

public class AccommodationDocumentationTestFilter extends Documentation {
    @Test
    public void 숙박업체_생성_요청() {
        RestAssured
                .given(this.spec)
                    .log().all()
                    .filter(AccommodationDocumentationTest.숙박업체_생성_요청());
    }
}
