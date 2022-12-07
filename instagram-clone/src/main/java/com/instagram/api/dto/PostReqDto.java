package com.instagram.api.dto;

import com.instagram.api.entity.Post;
import com.instagram.api.entity.User;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class PostReqDto {
    private MultipartFile file;
    private String caption;
    private String tags;

    public Post toEntity(String postImageUrl, User userEntity) {
        return Post.builder()
                .caption(caption)
                .postImageUrl(postImageUrl)
                .user(userEntity)
                .build();
    }
}
