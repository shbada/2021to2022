package com.example.stock_project.repository;

import com.example.stock_project.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

    /*
        충돌이 빈번하게 일어난다면 Optimistic Lock보다 성능 좋고, 데이터 정합성이 안정적
        단점: 별도의 Lock을 잡기 때문에 성능이 감소한다.
     */
    Stock getByProductId(Long productId);
}
