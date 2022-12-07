package com.book.jpa.chapter06.D다대다;

import com.book.jpa.chapter06.D다대다.MemberE;
import com.book.jpa.chapter06.D다대다.ProductE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductERepository extends JpaRepository<ProductE, Long> {
}
