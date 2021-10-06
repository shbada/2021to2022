package com.seohae.java.repository;

import com.seohae.java.dto.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
