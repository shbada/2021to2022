package com.instagram.api.repository;

import com.instagram.api.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    /** userId 의 게시글 조회 */
    Page<Post> findByUserId(Long id, Pageable pageable);
}