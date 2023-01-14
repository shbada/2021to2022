package com.book.jpa.api.repository;

import com.book.jpa.chapter05.Member;
import com.book.jpa.chapter05.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
