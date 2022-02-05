package com.book.jpa.chapter06.F다대다_한계극복;

import com.book.jpa.chapter06.D다대다.ProductE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductFRepository extends JpaRepository<ProductF, Long> {
}
