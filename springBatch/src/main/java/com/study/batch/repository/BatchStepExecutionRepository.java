package com.study.batch.repository;

import com.study.batch.entity.BatchStepExecution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BatchStepExecutionRepository extends JpaRepository<BatchStepExecution, Long> {

}
