package com.seohae.java.member.repository;

import com.seohae.java.member.dto.entity.Member;
import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Long> {
}
