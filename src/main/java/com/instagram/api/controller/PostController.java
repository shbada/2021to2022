package com.instagram.api.controller;

import com.instagram.api.common.response.CommonResponse;
import com.instagram.api.config.auth.PrincipalDetails;
import com.instagram.api.dto.PostReqDto;
import com.instagram.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/post")
public class PostController {
    private final CommonResponse commonResponse;
    private final PostService postService;

    /**
     * output test
     * @return
     */
    @GetMapping("/test")
    public ResponseEntity<?> hello() {
        return commonResponse.send("order test");
    }

    @PostMapping("")
    public ResponseEntity<?> addPost(@ModelAttribute PostReqDto postReqDto,
                                     @AuthenticationPrincipal PrincipalDetails principalDetails) {
        postService.addPost(postReqDto, principalDetails);

        return commonResponse.send();
    }
}
