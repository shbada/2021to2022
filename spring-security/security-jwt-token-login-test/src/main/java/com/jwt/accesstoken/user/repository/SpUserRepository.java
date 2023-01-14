package com.jwt.accesstoken.user.repository;

import com.jwt.accesstoken.user.domain.SpUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpUserRepository extends JpaRepository<SpUser, Long> {

    Optional<SpUser> findUserByEmail(String email);

}
