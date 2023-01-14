package com.api.shop.modules.repository;

import com.api.shop.modules.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // UserId 로 정보 조회
    Optional<Member> findByMemberName(String username);
}
