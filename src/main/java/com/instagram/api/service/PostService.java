package com.instagram.api.service;

import com.instagram.api.common.util.ImageUtil;
import com.instagram.api.config.auth.PrincipalDetails;
import com.instagram.api.dto.PostReqDto;
import com.instagram.api.dto.UserJoinReqDto;
import com.instagram.api.entity.Post;
import com.instagram.api.entity.User;
import com.instagram.api.repository.PostRepository;
import com.instagram.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;

    @Value("${file.path}")
    private String uploadFolder;

    /**
     * 게시글 등록
     * @param postReqDto
     * @param principalDetails
     */
    public void addPost(PostReqDto postReqDto, PrincipalDetails principalDetails) {
        String imageFileName = ImageUtil.SaveImage(uploadFolder, postReqDto.getFile());

        Post post = postReqDto.toEntity(imageFileName, principalDetails.getUser());

        postRepository.save(post);
    }

    /**
     * 로그인 유저의 게시글 조회
     * @param id
     * @param pageable
     * @return
     */
    public Page<Post> getPost(Long id, Pageable pageable) {
        Page<Post> posts = postRepository.findByUserId(id, pageable);
        return posts;
    }
}
