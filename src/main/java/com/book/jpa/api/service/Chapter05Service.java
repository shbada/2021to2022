package com.book.jpa.api.service;

import com.book.jpa.api.repository.MemberRepository;
import com.book.jpa.api.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Chapter05Service {
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;
}
