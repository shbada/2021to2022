package com.api.shop.modules.repository;

import com.api.shop.modules.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    void findByMemberIdx(long memberIdx);
}
