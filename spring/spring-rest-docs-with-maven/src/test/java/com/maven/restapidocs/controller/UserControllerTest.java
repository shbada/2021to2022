package com.maven.restapidocs.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import static com.maven.restapidocs.DocumentUtils.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void getUser() throws Exception {
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/user/{userId}", "seohae0001")
                )
                .andExpect(status().isOk())
                .andDo(document("get-user", getDocumentResponse(),
                        pathParameters(
                                parameterWithName("userId").description("사용자 아이디").attributes(getValidationAttribute("길이 : 8 ~ 12자" + newLine() + "조합 : 영문 + 숫자"))
                        ),
                        responseFields(
                                fieldWithPath("status").type(JsonFieldType.NUMBER).description("결과코드"),
                                fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"),
                                fieldWithPath("data.userId").type(JsonFieldType.STRING).description("유저아이디").attributes(getExampleAttribute("예) seohae1234")),
                                fieldWithPath("data.userName").type(JsonFieldType.STRING).description("유저명").attributes(getExampleAttribute("예) 김서해")),
                                fieldWithPath("data.age").type(JsonFieldType.NUMBER).description("나이")
                        )
                ));
    }

    @Test
    void getUserList() throws Exception {
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/user/users")
                )
                .andExpect(status().isOk())
                .andDo(document("get-users", getDocumentResponse(), responseFields(
                        fieldWithPath("status").type(JsonFieldType.NUMBER).description("결과코드"),
                        fieldWithPath("message").type(JsonFieldType.STRING).description("결과메시지"),
                        fieldWithPath("data[].userId").type(JsonFieldType.STRING).description("유저아이디").attributes(getExampleAttribute("예) seohae1234")),
                        fieldWithPath("data[].userName").type(JsonFieldType.STRING).description("유저명").attributes(getExampleAttribute("예) 김서해")),
                        fieldWithPath("data[].age").type(JsonFieldType.NUMBER).description("나이")
                )));
    }
}