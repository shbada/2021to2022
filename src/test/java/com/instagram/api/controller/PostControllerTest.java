package com.instagram.api.controller;

import com.instagram.api.IntegrationTest;
import com.instagram.api.config.jwt.JwtEnum;
import com.instagram.api.dto.PostReqDto;
import com.instagram.api.dto.UserJoinReqDto;
import com.instagram.api.dto.UserLoginReqDto;
import com.instagram.api.repository.PostRepository;
import com.instagram.api.service.PostService;
import com.instagram.api.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PostControllerTest extends IntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserService userService;

    @DisplayName("게시글 등록 성공")
    @Test
    void addPost_success() throws Exception {
        String username = "westssun";
        String jwtToken = this.joinAndLogin(username);

        String filePath = "/Users/seohae/Documents/seohae-dev/workspace/fileUpload/test.png";
        String contentType = "png";
        MockMultipartFile mockMultipartFile = getMockMultipartFile("file", "test", contentType, filePath);

        mockMvc.perform(multipart("/post")
                        .file(mockMultipartFile)
                        .param("tags", "test")
                        .param("caption", "test upload post")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .header(JwtEnum.HEADER_STRING.getValue(), jwtToken))
                .andExpect(status().isOk());

        assertEquals(postRepository.getById(1L).getId(), 1L);
    }

    private MockMultipartFile getMockMultipartFile(String paramName, String fileName, String contentType, String path)
            throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        return new MockMultipartFile(paramName, fileName + "." + contentType, contentType, fileInputStream);
    }
}