package com.book.jpa.api.repository;

import com.book.jpa.chapter05.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
