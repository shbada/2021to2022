package com.book.jpa.chapter06.F다대다_한계극복.api;

import com.book.jpa.chapter06.D다대다.MemberE;
import com.book.jpa.chapter06.F다대다_한계극복.MemberF;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberFRepository extends JpaRepository<MemberF, Long> {
}
