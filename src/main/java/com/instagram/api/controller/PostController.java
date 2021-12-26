package com.instagram.api.controller;

import com.instagram.api.common.response.CommonResponse;
import com.instagram.api.config.auth.PrincipalDetails;
import com.instagram.api.dto.PostReqDto;
import com.instagram.api.entity.Post;
import com.instagram.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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

    /**
     * 게시글 업로드
     * @param postReqDto
     * @param principalDetails
     * @return
     */
    @PostMapping("")
    public ResponseEntity<?> addPost(@ModelAttribute PostReqDto postReqDto,
                                     @AuthenticationPrincipal PrincipalDetails principalDetails) {
        postService.addPost(postReqDto, principalDetails);

        return commonResponse.send();
    }

    /**
     * 로그인 유저의 게시글 조회
     * @param principalDetails
     * @param pageable
     * @return
     */
    @GetMapping("")
    public ResponseEntity<?> post(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                  @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Post> pages = postService.getPost(principalDetails.getUser().getId(), pageable);
        return new ResponseEntity<>(pages, HttpStatus.OK); // MessageConverter 발동 = Jackson = 무한참조
    }
}
