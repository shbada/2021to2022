package com.example.membergradebatch.repository;

import com.example.membergradebatch.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
