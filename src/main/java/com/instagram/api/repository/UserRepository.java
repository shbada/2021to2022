package com.instagram.api.repository;

import com.instagram.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    /* username 에 해당하는 회원 존재 여부 */
    boolean existsByUsername(String username);
}