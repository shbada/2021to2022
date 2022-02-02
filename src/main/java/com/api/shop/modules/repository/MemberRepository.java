package com.api.shop.modules.repository;

import com.api.shop.modules.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
