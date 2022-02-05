package com.book.jpa.chapter06.F다대다_한계극복;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberProductRepository extends JpaRepository<MemberProduct, MemberProductId> {
}
