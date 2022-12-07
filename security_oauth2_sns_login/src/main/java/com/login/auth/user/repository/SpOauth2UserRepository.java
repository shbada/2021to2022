package com.login.auth.user.repository;

import com.login.auth.user.domain.SpOauth2User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * SpOauth2User
 */
public interface SpOauth2UserRepository extends JpaRepository<SpOauth2User, String> {

}
