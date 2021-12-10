package com.login.repository;

import com.login.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
    /**
     * 유저 아이디 존재 여부
     * @param userId
     * @return
     */
    boolean existsByUserId(String userId);

    /**
     * userId, password 체크
     * @param userId
     * @param password
     * @return
     */
    Users findByUserIdAndPassword(String userId, String password);
}
