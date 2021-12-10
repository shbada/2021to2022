package com.login.repository;

import com.login.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * 유저 아이디 존재 여부
     * @param userId
     * @return
     */
    boolean existsByUserId(String userId);
}
