package com.api.shop.modules.repository;

import com.api.shop.modules.entity.Cart;
import com.api.shop.modules.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
