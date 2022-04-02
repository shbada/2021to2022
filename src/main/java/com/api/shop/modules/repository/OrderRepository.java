package com.api.shop.modules.repository;

import com.api.shop.modules.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> getByMemberIdx(long memberIdx);
}
