package com.test.technology.repository;

import com.test.technology.entity.DeptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DeptRepository extends JpaRepository<DeptEntity, Integer> {
}
