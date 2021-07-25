package com.example.userservice.repository;

import com.example.userservice.entity.UserCnts;
import com.example.userservice.entity.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserCntsRepository extends CrudRepository<UserCnts, Long> {
}
