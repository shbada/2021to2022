package com.book.jpa.api.repository;

import com.book.jpa.chapter05.Member;
import com.book.jpa.chapter06.D다대다.MemberE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberERepository extends JpaRepository<MemberE, Long> {
}
