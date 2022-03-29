package com.api.shop.modules.repository;

import com.api.shop.modules.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
}
